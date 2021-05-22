import { Segment, Container, Grid, List, Header } from 'semantic-ui-react'

const Footer = () => {
    return (
        <Segment inverted vertical style={{ padding: '5em 0em' }}>
            <Container>
                <Grid divided inverted stackable>
                    <Grid.Row>
                        <Grid.Column width={3}>
                            <Header inverted as='h4' content='AdIsOn' />
                            <List link inverted>
                                <List.Item>Furkan Şahin</List.Item>
                                <List.Item>2017120357</List.Item>
                                <List.Item>Sivas Cumhuriyet Üniversitesi</List.Item>
                            </List>
                        </Grid.Column>
                        <Grid.Column width={3}>
                            <Header inverted as='h4' content='AdIsOn' />
                            <List link inverted>
                                <List.Item>Furkan Şahin</List.Item>
                                <List.Item>2017120357</List.Item>
                                <List.Item>Sivas Cumhuriyet Üniversitesi</List.Item>
                            </List>
                        </Grid.Column>
                        <Grid.Column width={7}>
                            <Header as='h4' inverted>
                                AdIsOn
                            </Header>
                            <p>
                                Sivas Cumhuriyet Üniversitesi, Furkan Şahin, 2017123057
                            </p>
                        </Grid.Column>
                    </Grid.Row>
                </Grid>
            </Container>
        </Segment>
    )
}

export default Footer;