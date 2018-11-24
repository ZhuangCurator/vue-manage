import axios from './index'

export const uploadImage = (param) => {
  return axios.uploadRequest({
    url: '/fileUpload/image',
    method: 'post',
    data: param
  })
}

export const uploadMedia = (param) => {
  return axios.uploadRequest({
    url: '/fileUpload/media',
    method: 'post',
    data: param
  })
}
