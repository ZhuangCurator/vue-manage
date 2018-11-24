const path = require('path')

const resolve = dir => {
  return path.join(__dirname, dir)
}

module.exports = {
  baseUrl: process.env.NODE_ENV === 'production' ? './' : '/',
  // 输出文件目录
  outputDir: 'current',
  // eslint-loader 是否在保存的时候检查
  lintOnSave: true,
  // 放置生成的静态资源 (js、css、img、fonts) 的 (相对于 outputDir 的) 目录。
  assetsDir: 'static',
  devServer: {
    port: 8090, // 端口号
    host: 'localhost',
    https: false, // https:{type:Boolean}
    open: true, // 配置自动启动浏览器
  },
  // 生产环境是否生成 sourceMap 文件，一般情况不建议打开
  productionSourceMap: false,

  chainWebpack: config => {
    config.resolve.alias
      .set('@', resolve('src')) // key,value自行定义，比如.set('@@', resolve('src/components'))
      .set('_c', resolve('src/components'))
  },

}
