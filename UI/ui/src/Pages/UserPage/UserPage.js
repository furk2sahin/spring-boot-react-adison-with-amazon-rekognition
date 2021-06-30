import React, { useContext, useEffect, useState } from 'react'
import Dropzone from 'react-dropzone'
import { useHistory } from 'react-router'
import { Button, Container, Grid, Icon, Image, Label, Menu, Segment } from 'semantic-ui-react'
import SessionContext from '../../Contexts/SessionContext'
import { getAds } from '../../Services/AdService'
import { createPost } from '../../Services/PostService'
import { compareFaces, detectTexts, getFaceCount, getModerationLabelsCount } from '../../Services/RekognitionService'



const UserPage = () => {
    const { user, setUser, setAuthenticated } = useContext(SessionContext)
    const [ads, setAds] = useState([])
    const [files, setFiles] = useState([])
    const [fileAccepted, setFileAccepted] = useState(false)
    const [base64Image, setBase64Image] = useState("")
    const [fileImg, setFileImage] = useState(false);
    const [requiredWord, setRequiredWord] = useState("");

    const history = useHistory()

    const handleLogout = () => {
        setAuthenticated('');
        setUser({});
        history.push("/")
    }

    useEffect(() => {
        let reader = new FileReader();
        setFileAccepted(false);
        files.forEach(file => {
            if (file.path.endsWith(".png") || file.path.endsWith(".jpg") || file.path.endsWith(".jpeg")) {
                setFileImage(true);
                reader.readAsDataURL(file);
                reader.onload = () => {
                    var base64 = reader.result
                    if (base64.startsWith("data")) {
                        base64 = base64.split(",")[1]
                    }
                    setBase64Image(base64);
                }
            } else {
                setFileImage(false);
            }
        })
    }, [files])

    useEffect(() => {
        const init = async () => {
            try {
                var { data } = await getModerationLabelsCount({ "base64Image": base64Image });
                var moderationLabels = data;
                var { data } = await getFaceCount({ "base64Image": base64Image });
                var faceCounts = data;
                var { data } = await compareFaces([{ "base64Image": user.image }, { "base64Image": base64Image }]);
                let isCompared = data;
                var { data } = await detectTexts({ "base64Image": base64Image })
                let containsRequiredWord = await data.textDetectionList.some(text => text.detectedText.toLocaleLowerCase() === requiredWord.toLocaleLowerCase())
                if (moderationLabels === 0 && faceCounts === 1 && isCompared && containsRequiredWord) {
                    setFileAccepted(true);
                } else {
                    setFileAccepted(false)
                }
            } catch (e) {
                console.log(e)
            }
        }

        if (fileImg)
            init();
    }, [base64Image])

    useEffect(() => {
        const init = async () => {
            try {
                const { data } = await getAds();
                setAds(data.adDtoList);
            } catch (e) {
                console.log(e)
            }
        }
        init();
    }, [])

    const handleClick = async (e, { value }) => {
        const post = {
            "userAdPostDto": {
                "accepted": false,
                "active": true,
                "description": "Katıldım",
                "image": base64Image,
                "userDto": { "id": user.id },
                "adDto": { "id": value }
            }
        }

        if (fileAccepted)
            try {
                const { data } = await createPost(post)
                setRequiredWord('')
                setFiles([])
                setFileAccepted(false)
                setBase64Image(false)
                setFileImage(false)
            } catch (e) {
                console.log(e)
            }
    }

    return (
        <>
            <Segment
                inverted
                textAlign='center'
                style={{ minHeight: 75, padding: '1em 0em' }}
                vertical
            >
                <Menu
                    fixed='top'
                    inverted
                    pointing
                    secondary
                    size='large'
                >
                    <Container>
                        <Menu.Item position="left" active>
                            <Image avatar spaced="right" src={`data:image/jpeg;base64,${user.image}`} />
                            {user.fullName}
                        </Menu.Item>

                        <Menu.Item position="right" active onClick={handleLogout}>
                            Logout
                        </Menu.Item>
                    </Container>
                </Menu>
            </Segment>

            <Container textAlign="center" style={{ marginTop: "5em" }}>
                {ads.map(ad =>
                    <Segment raised key={ad.id}>
                        <Label as='a' color='red'>
                            {ad.description}
                        </Label>
                        <Grid columns={3} style={{ marginTop: "3em" }}>
                            <Grid.Row>
                                <Grid.Column>

                                </Grid.Column>
                                <Grid.Column>
                                    <Image src={`data:image/jpeg;base64,${ad.image}`} />
                                </Grid.Column>
                                <Grid.Column>
                                    <Segment color={fileAccepted && ad.id !== 4 ? "green" : "red"}>
                                        <Dropzone onDrop={(acceptedFiles) => { setRequiredWord(ad.requiredWord); setFiles(acceptedFiles); }}>
                                            {({ getRootProps, getInputProps }) => (
                                                <section>
                                                    <div {...getRootProps()}>
                                                        <input {...getInputProps()} />
                                                        <p>Fotoğraf yükle <Icon name="file image" /></p>
                                                    </div>
                                                </section>
                                            )}
                                        </Dropzone>
                                    </Segment>
                                    {ad.id !== 4 ? files.map(file => (<li key={file.path}>
                                        {file.path} - {file.size} bytes
                                    </li>)) : null}
                                    <Button value={ad.id} primary onClick={handleClick}>Gönder</Button>
                                </Grid.Column>
                            </Grid.Row>
                        </Grid>
                    </Segment>
                )}

            </Container>


        </>
    )
}

export default UserPage
