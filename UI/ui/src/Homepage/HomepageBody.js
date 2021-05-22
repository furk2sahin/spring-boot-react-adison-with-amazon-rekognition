import { Segment, Grid, Header, Image, Container } from 'semantic-ui-react'

const HomepageBody = () => {
    return (
        <>
            <Segment style={{ padding: '8em 0em' }} vertical>
                <Grid container stackable verticalAlign='middle'>
                    <Grid.Row>
                        <Grid.Column width={8}>
                            <Header as='h3' style={{ fontSize: '2em' }}>
                                AdIsOn Nedir?
                        </Header>
                            <p style={{ fontSize: '1.33em' }}>
                                AdIsOn, reklam veren şirketlerin verdikleri reklamlar ile
                                tanıtımının yapılması ve reklamları alan vatandaşların da reklamdaki görevi gerçekleştirerek para kazanmasını amaçlamaktadır.
                        </p>
                        </Grid.Column>
                        <Grid.Column floated='right' width={6}>
                            <Image bordered rounded size='large' src='https://upload.wikimedia.org/wikipedia/commons/thumb/9/9d/Thomas_Edison2.jpg/220px-Thomas_Edison2.jpg' />
                        </Grid.Column>
                    </Grid.Row>
                </Grid>
            </Segment>

            <Segment style={{ padding: '0em' }} vertical>
                <Grid celled='internally' columns='equal' stackable>
                    <Grid.Row textAlign='center'>
                        <Grid.Column style={{ paddingBottom: '5em', paddingTop: '5em' }}>
                            <Header as='h3' style={{ fontSize: '2em' }}>
                                Eğer şirketseniz
                            <Image avatar size="tiny" src='https://img.rawpixel.com/s3fs-private/rawpixel_images/website_content/v286batch2-aew-company-logo-22_3.jpg?w=800&dpr=1&fit=default&crop=default&q=65&vib=3&con=3&usm=15&bg=F4F4F3&ixlib=js-2.2.1&s=311ef304bb090c4ba4f0ad95fad47fbf' />
                            </Header>
                            <p style={{ fontSize: '1.33em' }}>Reklam verebilirsiniz!</p>
                        </Grid.Column>
                        <Grid.Column style={{ paddingBottom: '5em', paddingTop: '5em' }}>
                            <Header as='h3' style={{ fontSize: '2em' }}>
                                Eğer kullanıcıysanız
                            <Image avatar size="tiny" src='https://www.pngitem.com/pimgs/m/80-800194_transparent-users-icon-png-flat-user-icon-png.png' />
                            </Header>
                            <p style={{ fontSize: '1.33em' }}>
                                Reklamlardaki görevi gerçekleştirip para kazanabilirsiniz!
                        </p>
                        </Grid.Column>
                    </Grid.Row>
                </Grid>
            </Segment>

            <Segment style={{ padding: '8em 0em' }} vertical>
                <Container text>
                    <Header as='h3' style={{ fontSize: '2em' }}>
                        Amacımız ne?
                </Header>
                    <p style={{ fontSize: '1.33em' }}>
                        <strong>AdIsOn</strong>'da amaç, şirketler tanıtımını yapıyorken vatandaşların da para kazanabilmeleridir.
                </p>
                </Container>
            </Segment>
        </>
    )
}

export default HomepageBody