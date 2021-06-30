import axios from 'axios'

const instance = axios.create({
    baseURL: 'http://localhost:8080/api'
})

export const createPost = (post) => {
    return instance.post(`/post`, post)
}

export const getPosts = () => {
    return instance.get("/posts")
}

export const getPost = (id) => {
    return instance.get(`/post/${id}`)
}

export const updatePost = (id, post) => {
    return instance.put(`/post/${id}`, post)
}

export const deletePost = (id) => {
    return instance.delete(`/post/${id}`)
}