import axios from 'axios'

const instance = axios.create({
    baseURL: 'http://localhost:8080/api'
})

export const createUser = (user) => {
    return instance.post('/user', user)
}

export const getUsers = () => {
    return instance.get('/users');
}

export const getUser = (id) => {
    return instance.get(`/user/id/${id}`)
}

export const updateUser = (id, user) => {
    return instance.put(`/user/${id}`, user)
}

export const deleteUser = (id) => {
    return instance.delete(`/user/${id}`)
}

export const getUserByUsername = (username) => {
    return instance.get(`/user/username/${username}`)
}