const devBaseUrl = 'http://localhost:19090/'
// const prodBaseUrl = 'http://192.168.31.5:18090/'
const prodBaseUrl = 'http://localhost:19090/'
const baseUrl = process.env.NODE_ENV === 'production' ? prodBaseUrl : devBaseUrl
export default {
  /**
   * @description token在Cookie中存储的时间，默认2小时 即1/12天
   */
  cookieExpires: 1/12,
  /**
   *@description api请求基础路径
   */
  baseUrl: baseUrl,
  /**
   * @description 默认打开的首页的路由name值，默认为home
   */
  homeName: 'home',
  /**
   * @description 统一的页面title
   */
  windowTitle: 'Vue后台管理系统',

}
