import { createMedia } from '@artsy/fresnel'
import React, { useContext, useEffect, useState } from 'react'
import { useHistory } from 'react-router'
import { Button, Container, Form, Grid, Header, Icon, Menu, Message, Segment, Sidebar, Visibility } from 'semantic-ui-react'
import AdContext from '../../Contexts/AdContext'
import CompanyContext from '../../Contexts/CompanyContext'
import SessionContext from '../../Contexts/SessionContext'
import { getAds } from '../../Services/AdService'
import { getCompanies, getCompany } from '../../Services/CompanyService'
import { getUsers } from '../../Services/UserService'

const LoginPage = () => {
    const history = useHistory()
    const [users, setUsers] = useState([])
    const [companies, setCompanies] = useState({})
    const [username, setUsername] = useState("")
    const [password, setPassword] = useState("")
    const { setUser, setAuthenticated } = useContext(SessionContext);
    const { setCompany } = useContext(CompanyContext)
    const { setAds } = useContext(AdContext);

    useEffect(() => {
        const init = async () => {
            try {
                const { data } = await getUsers();
                setUsers(data.userDtoList);
            } catch (e) {
                console.log(e)
            }

            try {
                const { data } = await getCompanies();
                setCompanies(data.companyDtoList);
            } catch (e) {
                console.log(e);
            }


        }
        init();
    }, [])

    const handleClick = () => {
        if (companies.some(company => company.userDto.userName === username && company.userDto.password === password)) {
            const company = companies.find(company => company.userDto.userName === username && company.userDto.password === password);
            setCompany(company);
            setAuthenticated("COMPANY");
            setTimeout(() => {
                history.push("/COMPANY")
            }, 1000);
            return
        }
        if (users.some(user => user.userName === username && user.password === password)) {
            const user = users.find(user => user.userName === username && user.password === password);
            setUser(user);
            setAuthenticated(user.userType);
            setTimeout(() => {
                history.push('/' + user.userType)
            }, 1000);
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
                        Hesabına giriş yap
            </Header>
                    <Form size='large'>
                        <Segment stacked >
                            <Form.Input
                                fluid
                                icon='user'
                                iconPosition='left'
                                placeholder='Username'
                                value={username}
                                onChange={(e, { value }) => setUsername(value)}
                            />
                            <Form.Input
                                value={password}
                                onChange={(e, { value }) => setPassword(value)}
                                fluid
                                icon='lock'
                                iconPosition='left'
                                placeholder='Password'
                                type='password'
                            />

                            <Button onClick={handleClick} color='teal' fluid size='large'>
                                Login
                    </Button>
                        </Segment>
                    </Form>
                    <Message>
                        Aramızda yeni misin? <a href="" onClick={() => { history.push("/register") }}>Kayıt ol</a>
                    </Message>
                </Grid.Column>
            </Grid >
        </>
    )
}

export default LoginPage