import axios from 'axios'

const instance = axios.create({
    baseURL: 'http://localhost:8080/api'
})

export const createAd = (ad) => {
    return instance.post(`/ad`, ad)
}

export const getAds = () => {
    return instance.get("/ads")
}

export const getAd = (id) => {
    return instance.get(`/ad/${id}`)
}

export const updateAd = (id, ad) => {
    return instance.put(`/ad/${id}`, ad)
}

export const deleteAd = (id) => {
    return instance.delete(`/ad/${id}`)
}