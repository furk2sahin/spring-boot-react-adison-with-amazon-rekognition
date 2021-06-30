import React from 'react'
import { Container, Menu, Segment } from 'semantic-ui-react'

const AdminPage = () => {
    return (
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
                    <Menu.Item position="right" active>Anasayfa</Menu.Item>
                </Container>
            </Menu>
        </Segment>
    )
}

export default AdminPage