import Vue from 'vue'
import Router from 'vue-router'
import routes from './router'
// import store from '@/store'
import iView from 'iview'
import { getUserId, getToken } from '@/libs/util'
import config from '@/config'
Vue.use(Router)

const router = new Router({
  routes,
  // mode: 'history', // default: hash ,history 这样设置后，就可以去掉url中丑丑的“#”啦
})

const LOGIN_PAGE_NAME = 'login'

router.beforeEach((to, from, next) => {
  iView.LoadingBar.start()
  const token = getToken()
  const userId = getUserId()
  if (!userId && !token && to.name !== LOGIN_PAGE_NAME) {
    // 未登录且要跳转的页面不是登录页
    next({
      name: LOGIN_PAGE_NAME // 跳转到登录页
    })
  } else if (!userId && !token && to.name === LOGIN_PAGE_NAME) {
    // 未登陆且要跳转的页面是登录页
    next() // 跳转
  }else if (!userId && token && to.name === LOGIN_PAGE_NAME) {
    // 已登录且要跳转的页面是登录页
    next({
      name: config.homeName // 跳转到homeName页
    })
  }else {
    next()
  }
})

router.afterEach(to => {
  iView.LoadingBar.finish()
  window.scrollTo(0, 0)
})

export default router
