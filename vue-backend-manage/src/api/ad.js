import axios from './index'

// 广告位分页
export const adPositionPage = (param) => {
  return axios.getRequest({
    url: '/ad/adPosition',
    params: param
  })
}

// 根据id查询广告位
export const adPosition = (param) => {
  return axios.getRequest({
    url: '/ad/adPosition/' +param.id ,
  })
}

// 查询广告类型
export const adType = () => {
  return axios.getRequest({
    url: '/ad/adType',
  })
}


export const adAdd = (param) => {
  return axios.httpRequest({
    url: '/ad/adPosition',
    method: 'post',
    data: param
  })
}

export const adPositionStatus = (param) => {
  return axios.httpRequest({
    url: '/ad/adPositionStatus',
    method: 'put',
    data: param
  })
}

export const adEdit = (param) => {
  return axios.httpRequest({
    url: '/ad/adPosition',
    method: 'put',
    data: param
  })
}

// 广告内容分页
export const adContentPage = (param) => {
  return axios.getRequest({
    url: '/ad/adContent',
    params: param
  })
}

// 根据id和positionId查询广告位
export const adContent = (param) => {
  return axios.getRequest({
    url: '/ad/adContent/' +param.id,
  })
}

// 广告内容启用停用
export const adContentStatus = (param) => {
  return axios.httpRequest({
    url: '/ad/adContentStatus',
    method: 'put',
    data: param
  })
}

// 广告内容编辑
export const adContentEdit = (param) => {
  return axios.httpRequest({
    url: '/ad/adContent',
    method: 'put',
    data: param
  })
}

export const adContentAdd = (param) => {
  return axios.httpRequest({
    url: '/ad/adContent',
    method: 'post',
    data: param
  })
}

// 操作系统 系统版本
export const adContentParam = () => {
  return axios.getRequest({
    url: '/ad/adContentParam',
  })
}
