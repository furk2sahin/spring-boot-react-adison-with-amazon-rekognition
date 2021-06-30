import React, { useContext, useEffect, useState } from 'react'
import { useHistory } from 'react-router';
import { Button, Container, Divider, Feed, Grid, Icon, Image, Label, Menu, Segment } from 'semantic-ui-react'
import CompanyContext from '../../Contexts/CompanyContext'
import PostContext from '../../Contexts/PostContext';
import SessionContext from '../../Contexts/SessionContext';
import { getAds } from '../../Services/AdService';
import { deletePost, getPosts, updatePost } from '../../Services/PostService';

const CompanyPage = () => {

    const { setAuthenticated } = useContext(SessionContext)
    const { company, setCompany } = useContext(CompanyContext);
    const [ads, setAds] = useState([])
    const [posts, setPosts] = useState([])
    const history = useHistory()
    useEffect(() => {
        const init = async () => {
            console.log(company);
            try {
                const { data } = await getAds();
                setAds(data.adDtoList.filter(element => element.companyDto.id === company.id))
                console.log(data, data.adDtoList.filter(element => element.companyDto.id === company.id))
            } catch (e) {
                console.log(e);
            }
            console.log(ads)
            console.log(posts)
        }

        init();
    }, [company])

    useEffect(() => {
        const init = async () => {
            try {
                const { data } = await getPosts();
                setPosts(data.userAdPostDtoList.filter(element => ads.some(ad => ad.id === element.adDto.id)));
                console.log(data, data.userAdPostDtoList.filter(element => ads.some(ad => ad.id === element.adDto.id)))
            } catch (e) {
                console.log(e)
            }
        }

        init();
    }, [ads])

    const handleLogout = () => {
        setAuthenticated('');
        setCompany({});
        setAds([]);
        setPosts([])
        history.push("/")
    }

    const delPost = async (e, { value }) => {
        try {
            const { data } = await deletePost(value)
            console.log(data);
        } catch (e) {
            console.log(e)
        }
        try {
            const { data } = await getPosts();
            setPosts(data.userAdPostDtoList.filter(element => ads.some(ad => ad.id === element.adDto.id)));
        } catch (e) {
            console.log(e)
        }
    }

    const upPost = async (e, { value }) => {

        const postToUpdate = {
            "accepted": true,
            "description": value.description,
            "active": true
        }

        try {
            const { data } = await updatePost(value.id, { "userAdPostDto": postToUpdate })
        } catch (e) {
            console.log(e)
        }

        try {
            const { data } = await getPosts();
            setPosts(data.userAdPostDtoList.filter(element => ads.some(ad => ad.id === element.adDto.id)));
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
                            <Image avatar spaced="right" src={`data:image/jpeg;base64,${company.image}`} />
                            {company.name}
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
                                    {posts.map(post => post.adDto.id === ad.id && !post.accepted ?
                                        <Segment>
                                            <Feed>
                                                <Feed.Event>
                                                    <Feed.Label>
                                                        <img src={`data:image/jpeg;base64,${post.userDto.image}`} />
                                                    </Feed.Label>
                                                    <Feed.Content>
                                                        <Feed.Summary>
                                                            <Feed.User>{post.userDto.fullName}</Feed.User> bu reklama katıldı.
                                                        <Feed.Date>{post.createDate}</Feed.Date>
                                                        </Feed.Summary>
                                                        <Feed.Extra images>
                                                            <a>
                                                                <img src={`data:image/jpeg;base64,${post.image}`} />
                                                            </a>
                                                        </Feed.Extra>
                                                        <Feed.Meta>
                                                            <Button onClick={upPost} value={{ "id": post.id, "description": post.description }}>
                                                                <Feed.Like >
                                                                    <Icon name='check' /> Kabul Et
                                                            </Feed.Like>
                                                            </Button>

                                                            <Button onClick={delPost} value={post.id}>
                                                                <Feed.Like >
                                                                    <Icon name='times' /> Geri Çevir
                                                            </Feed.Like>
                                                            </Button>

                                                        </Feed.Meta>
                                                    </Feed.Content>
                                                </Feed.Event>
                                            </Feed>
                                        </Segment>
                                        : null
                                    )}

                                </Grid.Column>
                            </Grid.Row>
                        </Grid>
                    </Segment>


                )}
            </Container>
        </>
    )
}

export default CompanyPage