import React from 'react'
import Footer from '../Footer/Footer'
import HomepageBody from './HomepageBody'
import ResponsiveContainer from './ResponsiveContainer'

const Homepage = () => {
    return (
        <ResponsiveContainer>
            <HomepageBody />
            <Footer />
        </ResponsiveContainer>
    )
}


export default Homepage;
