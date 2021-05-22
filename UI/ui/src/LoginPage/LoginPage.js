import React from 'react'
import { Button, Form, Grid, Header, Image, Message, Segment } from 'semantic-ui-react'

const LoginForm = () => (
    <Grid inverted textAlign='center' style={{ height: '100vh' }} verticalAlign='middle'>
        <Grid.Column style={{ maxWidth: 450 }}>
            <Header as='h2' color='teal' textAlign='center'>
                Hesabına giriş yap
            </Header>
            <Form size='large'>
                <Segment stacked >
                    <Form.Input fluid icon='user' iconPosition='left' placeholder='E-mail' />
                    <Form.Input
                        fluid
                        icon='lock'
                        iconPosition='left'
                        placeholder='Password'
                        type='password'
                    />

                    <Button color='teal' fluid size='large'>
                        Login
                    </Button>
                </Segment>
            </Form>
            <Message>
                Aramızda yeni misin? <a href='#'>Kayıt ol</a>
            </Message>
        </Grid.Column>
    </Grid>
)

export default LoginForm