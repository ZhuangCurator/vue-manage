import axios from './index'

export const albumCategoryList = (param) => {
  return axios.getRequest({
    url: '/albumCategory',
    params: param
  })
}

export const albumCategoryAdd = (param) => {
  return axios.httpRequest({
    url: '/albumCategory',
    method: 'post',
    data: param
  })
}

export const albumCategoryStatus = (param) => {
  return axios.httpRequest({
    url: '/albumCategory/categoryStatus',
    method: 'put',
    data: param
  })
}

export const albumCategoryEdit = (param) => {
  return axios.httpRequest({
    url: '/albumCategory',
    method: 'put',
    data: param
  })
}
