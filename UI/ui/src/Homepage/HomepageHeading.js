import { Container, Header } from 'semantic-ui-react'
import PropTypes from 'prop-types'

const HomepageHeading = ({ mobile, content1, content2 }) => (
    <Container text>
        <Header
            as='h1'
            content={content1}
            inverted
            style={{
                fontSize: mobile ? '2em' : '4em',
                fontWeight: 'normal',
                marginBottom: 0,
                marginTop: mobile ? '1.5em' : '3em',
            }}
        />
        <Header
            as='h2'
            content={content2}
            inverted
            style={{
                fontSize: mobile ? '1.5em' : '1.7em',
                fontWeight: 'normal',
                marginTop: mobile ? '0.5em' : '1.5em',
            }}
        />
    </Container>
)

HomepageHeading.propTypes = {
    mobile: PropTypes.bool,
}

export default HomepageHeading