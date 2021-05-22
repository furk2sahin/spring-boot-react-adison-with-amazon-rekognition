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
import PropTypes from 'prop-types'
import React, { useState } from 'react'
import Footer from '../Footer/Footer'
import HomepageBody from './HomepageBody'
import HomepageHeading from './HomepageHeading'

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

ResponsiveContainer.propTypes = {
    children: PropTypes.node,
}

const DesktopContainer = ({ children }) => {
    const [fixed, setFixedMenu] = useState(false);


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
                            <Menu.Item as='a' active>AdIsOn</Menu.Item>
                            <Menu.Item as='a'>Şirketler</Menu.Item>
                            <Menu.Item as='a'>Neden AdIsOn?</Menu.Item>
                            <Menu.Item position='right'>
                                <Button as='a' inverted={!fixed}>Giriş Yap</Button>
                                <Button as='a' inverted={!fixed} primary={fixed} style={{ marginLeft: '0.5em' }}>
                                    Kayıt Ol
                                    </Button>
                            </Menu.Item>
                        </Container>
                    </Menu>
                    <HomepageHeading />
                </Segment>
            </Visibility>
            {children}
        </Media>
    )
}

DesktopContainer.propTypes = {
    children: PropTypes.node,
}

const MobileContainer = ({ children }) => {
    const [sidebarOpened, setHandleSidebar] = useState(false);

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
                    <Menu.Item as='a' active>AdIsOn</Menu.Item>
                    <Menu.Item as='a'>Şirketler</Menu.Item>
                    <Menu.Item as='a'>Neden AdIsOn?</Menu.Item>
                    <Menu.Item as='a'>Giriş Yap</Menu.Item>
                    <Menu.Item as='a'>Kayıt Ol</Menu.Item>
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
                                    <Button as='a' inverted>
                                        Giriş Yap
                                    </Button>
                                    <Button as='a' inverted style={{ marginLeft: '0.5em' }}>
                                        Kayıt Ol
                                    </Button>
                                </Menu.Item>
                            </Menu>
                        </Container>
                        <HomepageHeading mobile />
                    </Segment>
                    {children}
                </Sidebar.Pusher>
            </Sidebar.Pushable>
        </Media>
    )
}

MobileContainer.propTypes = {
    children: PropTypes.node,
}

const HomepageLayout = () => (
    <ResponsiveContainer>
        <HomepageBody />
        <Footer />
    </ResponsiveContainer>
)

export default HomepageLayout;
