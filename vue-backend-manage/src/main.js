import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import iView from 'iview'
import config from '@/config'
import './index.less'
// import '@/assets/icons/iconfont.css'
import '@/assets/icon-font/iconfont.css'
// 自定义全局组件
import components from '@/components'
// 自定义校验规则
import validate from '@/libs/validate'
import customPlugins from './plugins'

Vue.use(components)
Vue.use(iView)
Vue.use(customPlugins)

Vue.config.productionTip = false

// 全局注册应用配置
Vue.prototype.$config = config
// 全局注册校验方法
Vue.prototype.$validate = validate

document.title = config.windowTitle

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
