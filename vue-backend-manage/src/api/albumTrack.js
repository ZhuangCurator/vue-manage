import axios from './index'

export const albumTrackPage = (param) => {
  return axios.getRequest({
    url: '/albumTrack',
    params: param
  })
}

export const albumTrackAdd = (param) => {
  return axios.httpRequest({
    url: '/albumTrack',
    method: 'post',
    data: param
  })
}

export const albumTrackStatus = (param) => {
  return axios.httpRequest({
    url: '/albumTrack/status',
    method: 'put',
    data: param
  })
}

export const albumTrackBatchStatus = (param) => {
  return axios.httpRequest({
    url: '/albumTrack/batchStatus',
    method: 'put',
    data: param
  })
}

export const albumTrackEdit = (param) => {
  return axios.httpRequest({
    url: '/albumTrack',
    method: 'put',
    data: param
  })
}

