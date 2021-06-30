import axios from 'axios'

const instance = axios.create({
    baseURL: 'http://localhost:8080/api'
})

export const createCompany = (company) => {
    return instance.post('/company', company)
}

export const getCompanies = () => {
    return instance.get("/companies")
}

export const getCompany = (id) => {
    return instance.get(`/company/${id}`)
}

export const updateCompany = (id, company) => {
    return instance.put(`/company/${id}`, company)
}

export const deleteCompany = (id) => {
    return instance.delete(`/company/${id}`)
}