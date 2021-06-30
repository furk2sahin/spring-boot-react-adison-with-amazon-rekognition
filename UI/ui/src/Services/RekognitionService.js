import axios from 'axios'

const instance = axios.create({
    baseURL: 'http://localhost:8080/api'
})

export const detectLabels = (base64Image) => {
    return instance.post('/images/detect-labels', base64Image)
}

export const detectTexts = (base64Image) => {
    return instance.post('/images/detect-texts', base64Image)
}

export const compareFaces = (images) => {
    return instance.post(`/compare-faces/`, images)
}

export const detectFaces = (base64Image) => {
    return instance.post('/images/detect-faces', base64Image)
}

export const createCollection = (name) => {
    return instance.post(`/collection/${name}`)
}

export const getCollections = () => {
    return instance.get(`/collections`)
}

export const deleteCollection = (name) => {
    return instance.delete(`/collection/${name}`)
}

export const describeCollection = (name) => {
    return instance.get(`/collection/describe-collection/${name}`)
}

export const indexFaces = (name, id) => {
    return instance.post(`/collection/index-faces/${name}/${id}`)
}

export const listFaces = (name) => {
    return instance.get(`/collection/list-faces/${name}`)
}

export const deleteFaces = (name, faceId) => {
    return instance.delete(`/collection/delete-faces/${name}/${faceId}`)
}

export const searchFaces = (name, base64Image) => {
    return instance.post(`/collection/search-faces/${name}`, base64Image)
}

export const getFaceCount = (base64Image) => {
    return instance.post(`/collection/get-face-count`, base64Image)
}

export const getModerationLabelsCount = (base64Image) => {
    return instance.post(`/collection/get-moderation-labels-count`, base64Image)
}

export const getFaceUserId = (name, base64Image) => {
    return instance.post(`/collection/get-face-user-id/${name}`, base64Image)
}

export const getFaceIdByUserId = (name, id) => {
    return instance.get(`/collection/get-face-id-by-user-id/${name}`, id)
}