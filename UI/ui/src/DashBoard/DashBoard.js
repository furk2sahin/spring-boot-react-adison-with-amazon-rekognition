import React, { useState } from 'react'
import { Route, Switch } from 'react-router'
import CompanyContext from '../Contexts/CompanyContext'
import SessionContext from '../Contexts/SessionContext'
import AdContext from '../Contexts/AdContext'
import PostContext from '../Contexts/PostContext'
import Homepage from '../Homepage/Homepage'
import LoginPage from '../Pages/LoginPage/LoginPage'
import RegisterPage from '../Pages/RegisterPage/RegisterPage'
import UserPage from '../Pages/UserPage/UserPage'
import AdminPage from '../Pages/AdminPage/AdminPage'
import CompanyPage from '../Pages/CompanyPage/CompanyPage'

const DashBoard = () => {
    const [authenticated, setAuthenticated] = useState('')
    const [user, setUser] = useState({})
    const [company, setCompany] = useState({})
    const [companies, setCompanies] = useState({})
    const [ad, setAd] = useState({})
    const [ads, setAds] = useState({})
    const [post, setPost] = useState({})
    const [posts, setPosts] = useState({})
    return (
        <SessionContext.Provider value={{ user, setUser, authenticated, setAuthenticated }}>
            <CompanyContext.Provider value={{ company, setCompany, companies, setCompanies }}>
                <AdContext.Provider value={{ ad, setAd, ads, setAds }}>
                    <PostContext.Provider value={{ post, setPost, posts, setPosts }}>
                        <Switch>
                            <Route exact path="/">
                                <Homepage />
                            </Route>
                            <Route exact path="/register">
                                <RegisterPage />
                            </Route>
                            <Route exact path="/login">
                                <LoginPage />
                            </Route>
                            <Route exact path="/USER">
                                <UserPage />
                            </Route>
                            <Route exact path="/ADMIN">
                                <AdminPage />
                            </Route>
                            <Route exact path="/COMPANY">
                                <CompanyPage />
                            </Route>
                        </Switch>
                    </PostContext.Provider>
                </AdContext.Provider>
            </CompanyContext.Provider>
        </SessionContext.Provider>
    )
}

export default DashBoard