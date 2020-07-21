import axios from 'axios'
import store from '@/store'
import Message from 'iview/src/components/message'
import router from '@/router'
import { getToken } from '@/libs/util'

class HttpRequest {
  // 每个类都必须有的
  constructor (baseUrl = baseURL) {
    this.baseUrl = baseUrl
  }

  httpRequestConfig () {
    const token = getToken()
    return {
      baseURL: this.baseUrl,
      headers: {
        Authorization: token
      }

    }
  }

  uploadRequestConfig () {
    const token = store.state.token
    const userId = store.state.userId
    const config = {
      baseURL: this.baseUrl,
      headers: {
        userId: userId,
        token: token,
        'Content-Type': 'multipart/form-data'
      }

    }
    return config
  }

  // 拦截器拦截请求
  interceptors (instance) {
    // 请求拦截器
    instance.interceptors.request.use(config => {
      // 添加全局的loading
      console.log('interceptors#request:', config)
      return config
    }, error => {
      return Promise.reject(error)
    })

    // 响应拦截器
    instance.interceptors.response.use(res => {
      const { data } = res
      console.log('interceptors#response#data:', data)
      if (data.status === -1) {
        Message.error({
          content: '登录信息过期，请重新登录...',
          duration: 3
        })
        router.push('login')
      } else if (data.status === 0) {
        Message.error({
          content: data.msg,
          duration: 3
        })
      }
      return data
    }, error => {
      //
      return Promise.reject(error)
    })
  }

  httpRequest (options) {
    const instance = axios.create()
    options = Object.assign(this.httpRequestConfig(), options) // 数据拼接 相同的数据后面覆盖前面
    this.interceptors(instance)
    return instance(options)
  }
  getRequest (options) {
    const url = options.url
    const params = options.params

    const instance = axios.create(this.httpRequestConfig())
    this.interceptors(instance)
    return instance.get(url, { params })
  }

  uploadRequest (options) {
    const instance = axios.create()
    options = Object.assign(this.uploadRequestConfig(), options) // 数据拼接 相同的数据后面覆盖前面
    this.interceptors(instance)
    return instance(options)
  }
}

export default HttpRequest
