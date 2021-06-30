import React, { useContext, useEffect, useState } from 'react'
import Dropzone from 'react-dropzone';
import { useHistory } from 'react-router';
import { Button, Container, Form, Grid, Header, Icon, Menu, Message, Segment } from 'semantic-ui-react'
import SessionContext from '../../Contexts/SessionContext';
import { getFaceCount, getModerationLabelsCount } from '../../Services/RekognitionService';
import { createUser } from '../../Services/UserService';

const RegisterPage = () => {
    const { setUser, setAuthenticated } = useContext(SessionContext)
    const history = useHistory()
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [username, setUsername] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [phone, setPhone] = useState("");
    const [description, setDescription] = useState("");
    const [files, setFiles] = useState([]);
    const [base64Image, setBase64Image] = useState("");
    const [fileAccepted, setFileAccepted] = useState(false);
    const [fileImg, setFileImage] = useState(false);

    useEffect(() => {
        let reader = new FileReader();
        setFileAccepted(false);
        files.forEach(file => {
            if (file.path.endsWith(".png") || file.path.endsWith(".jpg") || file.path.endsWith(".jpeg")) {
                setFileImage(true);
                reader.readAsDataURL(file);
                reader.onload = () => {
                    setBase64Image(reader.result);
                }
            } else {
                setFileImage(false);
            }
        })
    }, [files])

    useEffect(() => {
        const init = async () => {
            try {
                var { data } = await getModerationLabelsCount({ "base64Image": base64Image.split(",")[1] });
                var moderationLabels = data;
                var { data } = await getFaceCount({ "base64Image": base64Image.split(",")[1] });
                var faceCounts = data;
                if (moderationLabels === 0 && faceCounts === 1) {
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

    const handleFirstName = (e, { value }) => {
        setFirstName(value);
    }

    const handleLastName = (e, { value }) => {
        setLastName(value);
    }

    const handleUsername = (e, { value }) => {
        setUsername(value);
    }

    const handleEmail = (e, { value }) => {
        setEmail(value);
    }

    const handlePassword = (e, { value }) => {
        setPassword(value);
    }

    const handlePhone = (e, { value }) => {
        setPhone(value);
    }

    const handleDescription = (e, { value }) => {
        setDescription(value);
    }

    const handleClick = async () => {
        const user = {
            "userDto": {
                "fullName": firstName + " " + lastName,
                "userName": username,
                "email": email,
                "password": password,
                "phone": phone,
                "userType": "USER",
                "active": true,
                "description": description,
                "image": base64Image
            }
        }

        if (fileAccepted)
            try {
                const { data } = await createUser(user)
                setUser(data);
                setAuthenticated(data.userDto.userType)
                setTimeout(() => {
                    history.push("/" + data.userDto.userType)
                }, 1000);
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
                        <Menu.Item active onClick={() => history.push("/")}>Anasayfa</Menu.Item>
                    </Container>
                </Menu>
            </Segment>
            <Grid inverted textAlign='center' style={{ height: '100vh' }} verticalAlign='middle'>
                <Grid.Column style={{ maxWidth: 450 }}>
                    <Header as='h2' color='teal' textAlign='center'>
                        Kayıt Ol
                    </Header>
                    <Form size='large'>
                        <Segment stacked >
                            <Form.Input value={firstName} onChange={handleFirstName} fluid icon='user' iconPosition='left' placeholder='Adınız' />
                            <Form.Input value={lastName} onChange={handleLastName} fluid icon='user' iconPosition='left' placeholder='Soyadınız' />
                            <Form.Input value={username} onChange={handleUsername} fluid icon='user' iconPosition='left' placeholder='Kullanıcı adı' />
                            <Form.Input value={email} onChange={handleEmail} fluid type="email" icon='user' iconPosition='left' placeholder='Email' />
                            <Form.Input
                                value={password}
                                onChange={handlePassword}
                                fluid
                                icon='lock'
                                iconPosition='left'
                                placeholder='Şifre'
                                type='password'
                            />
                            <Form.Input value={phone} onChange={handlePhone} fluid icon='phone' iconPosition='left' placeholder='Telefon' />
                            <Form.Input value={description} onChange={handleDescription} fluid icon='user' iconPosition='left' placeholder='Senin hakkında...' />
                            <Segment color={fileAccepted ? "green" : "red"}>
                                <Dropzone onDrop={acceptedFiles => setFiles(acceptedFiles)}>
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
                            {files.map(file => (<li key={file.path}>
                                {file.path} - {file.size} bytes
                            </li>))}
                            <Button color='teal' onClick={handleClick} fluid size='large'>
                                Login
                            </Button>
                        </Segment>
                    </Form>
                    <Message>
                        Hesabın var mı? <a href="" onClick={() => { history.push("/login") }}>Giriş yap</a>
                    </Message>
                </Grid.Column>
            </Grid>
        </>
    )

}

export default RegisterPage