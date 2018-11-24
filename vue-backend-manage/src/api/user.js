import axios from './index'

// 用户分页查询
export const userPage = (param) => {
  return axios.getRequest({
    url: '/userPage',
    method: 'get',
    params: param
  })
}

export const getUser = (param) => {
  return axios.getRequest({
    url: '/user/' + param.id,
    method: 'get',
  })
}

export const addUser = (param) => {
  return axios.httpRequest({
    url: '/addUser',
    method: 'post',
    data: param
  })
}

export const userExport = (param) => {
  return axios.httpRequest({
    url: '/exportUser',
    method: 'post',
    data: param,
    responseType: 'blob'
  })
}

export const userImportTemplate = (param) => {
  return axios.httpRequest({
    url: '/importUserTemplate',
    method: 'post',
    data: param,
    responseType: 'blob'
  })
}

export const userImport = (param) => {
  return axios.uploadRequest({
    url: '/importUser',
    method: 'post',
    data: param
  })
}

export const editUser = (param) => {
  return axios.httpRequest({
    url: '/editUser',
    method: 'put',
    data: param
  })
}

export const userRemoved = (param) => {
  return axios.httpRequest({
    url: '/userRemoved',
    method: 'put',
    data: param
  })
}

// 后台用户登录
export const login = (param) => {
  return axios.httpRequest({
    url: '/user/login',
    method: 'post',
    data: param
  })
}
