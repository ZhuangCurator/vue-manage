import axios from './index'

export const albumPage = (param) => {
  return axios.getRequest({
    url: '/album',
    params: param
  })
}

export const getRegion = () => {
  return axios.getRequest({
    url: '/user/region',
    method: 'get',
  })
}

export const album = (param) => {
  return axios.getRequest({
    url: '/album/' + param.id,
  })
}

export const albumAdd = (param) => {
  return axios.httpRequest({
    url: '/album',
    method: 'post',
    data: param
  })
}

export const albumStatus = (param) => {
  return axios.httpRequest({
    url: '/album/status',
    method: 'put',
    data: param
  })
}

export const albumEdit = (param) => {
  return axios.httpRequest({
    url: '/album',
    method: 'put',
    data: param
  })
}
