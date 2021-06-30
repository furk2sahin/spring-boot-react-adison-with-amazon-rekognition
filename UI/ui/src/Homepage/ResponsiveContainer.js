import { createMedia } from '@artsy/fresnel'
import {
    Visibility,
    Segment,
    Menu,
    Container,
    Button,
    Sidebar,
    Icon
} from 'semantic-ui-react'
import React, { useState } from 'react'
import HomepageHeading from './HomepageHeading'
import { useHistory } from 'react-router'

const { Media, MediaContextProvider } = createMedia({
    breakpoints: {
        mobile: 0,
        tablet: 768,
        computer: 1024,
    },
})

const ResponsiveContainer = ({ children }) => (
    <MediaContextProvider>
        <DesktopContainer>{children}</DesktopContainer>
        <MobileContainer>{children}</MobileContainer>
    </MediaContextProvider>
)

const DesktopContainer = ({ children }) => {
    const [fixed, setFixedMenu] = useState(false);
    const [activeItem, setActiveItem] = useState("AdIsOn");
    const history = useHistory()

    return (
        <Media greaterThan='mobile'>
            <Visibility
                once={false}
                onBottomPassed={() => setFixedMenu(true)}
                onBottomPassedReverse={() => setFixedMenu(false)}
            >
                <Segment
                    inverted
                    textAlign='center'
                    style={{ minHeight: 700, padding: '1em 0em' }}
                    vertical
                >
                    <Menu
                        fixed={fixed ? 'top' : null}
                        inverted={!fixed}
                        pointing={!fixed}
                        secondary={!fixed}
                        size='large'
                    >
                        <Container>
                            <Menu.Item active={activeItem === 'AdIsOn'} onClick={() => setActiveItem("AdIsOn")} name="AdIsOn">AdIsOn</Menu.Item>
                            <Menu.Item active={activeItem === 'Şirketler'} onClick={() => setActiveItem("Şirketler")} name="Şirketler">Şirketler</Menu.Item>
                            <Menu.Item active={activeItem === 'Neden AdIsOn'} onClick={() => setActiveItem("Neden AdIsOn")} name="Neden AdIsOn">Neden AdIsOn?</Menu.Item>
                            <Menu.Item position='right'>
                                <Button onClick={() => { history.push("/login") }} inverted={!fixed}>Giriş Yap</Button>
                                <Button onClick={() => { history.push("/register") }} inverted={!fixed} primary={fixed} style={{ marginLeft: '0.5em' }}>
                                    Kayıt Ol
                                    </Button>
                            </Menu.Item>
                        </Container>
                    </Menu>
                    {
                        activeItem === "AdIsOn" ? <HomepageHeading content1="AdIsOn" content2="Reklam ver. Reklam al." /> :
                            activeItem === "Şirketler" ? <HomepageHeading content1="Trendyol, Hepsiburada..." /> :
                                activeItem === "Neden AdIsOn" ? <HomepageHeading content2="AdIsOn'da amaç, şirketler tanıtımını yapıyorken vatandaşların da para kazanabilmeleridir." /> :
                                    null
                    }
                </Segment>
            </Visibility>
            {children}
        </Media>
    )
}

const MobileContainer = ({ children }) => {
    const [activeItem, setActiveItem] = useState("AdIsOn");
    const [sidebarOpened, setHandleSidebar] = useState(false);
    const history = useHistory()
    return (
        <Media as={Sidebar.Pushable} at='mobile'>
            <Sidebar.Pushable>
                <Sidebar
                    as={Menu}
                    animation='overlay'
                    inverted
                    onHide={() => setHandleSidebar(false)}
                    vertical
                    visible={sidebarOpened}
                >
                    <Menu.Item active={activeItem === 'AdIsOn'} onClick={() => setActiveItem("AdIsOn")} name="AdIsOn">AdIsOn</Menu.Item>
                    <Menu.Item active={activeItem === 'Şirketler'} onClick={() => setActiveItem("Şirketler")} name="Şirketler">Şirketler</Menu.Item>
                    <Menu.Item active={activeItem === 'Neden AdIsOn'} onClick={() => setActiveItem("Neden AdIsOn")} name="Neden AdIsOn">Neden AdIsOn?</Menu.Item>
                    <Menu.Item onClick={() => { history.push("/login") }}>Giriş Yap</Menu.Item>
                    <Menu.Item onClick={() => { history.push("/register") }}>Kayıt Ol</Menu.Item>
                </Sidebar>

                <Sidebar.Pusher dimmed={sidebarOpened}>
                    <Segment
                        inverted
                        textAlign='center'
                        style={{ minHeight: 350, padding: '1em 0em' }}
                        vertical
                    >
                        <Container>
                            <Menu inverted pointing secondary size='large'>
                                <Menu.Item onClick={() => setHandleSidebar(true)}>
                                    <Icon name='sidebar' />
                                </Menu.Item>
                                <Menu.Item position='right'>
                                    <Button onClick={() => { history.push("/login") }} inverted>
                                        Giriş Yap
                                    </Button>
                                    <Button onClick={() => { history.push("/register") }} inverted style={{ marginLeft: '0.5em' }}>
                                        Kayıt Ol
                                    </Button>
                                </Menu.Item>
                            </Menu>
                        </Container>
                        {
                            activeItem === "AdIsOn" ? <HomepageHeading mobile content1="AdIsOn" content2="Reklam ver. Reklam al." /> :
                                activeItem === "Şirketler" ? <HomepageHeading mobile content1="Trendyol, Hepsiburada..." /> :
                                    activeItem === "Neden AdIsOn" ? <HomepageHeading mobile content2="AdIsOn'da amaç, şirketler tanıtımını yapıyorken vatandaşların da para kazanabilmeleridir." /> :
                                        null
                        }
                    </Segment>
                    {children}
                </Sidebar.Pusher>
            </Sidebar.Pushable>
        </Media>
    )
}


export default ResponsiveContainer