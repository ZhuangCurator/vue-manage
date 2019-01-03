import Main from '@/components/main'
export default [
  {
    path: '/login',
    name: 'login',
    meta: {
      title: 'Login - 登录',
      hideInMenu: true
    },
    component: () => import('@/views/login/login.vue')
  },
  {
    path: '/',
    name: '_home',
    redirect: '/home',
    component: Main,
    meta: {
      hideInMenu: true,
      notCache: true
    },
    children: [
      {
        path: '/home',
        name: 'home',
        meta: {
          hideInMenu: true,
          title: '首页',
          notCache: true,
          icon: 'md-home'
        },
        component: () => import('@/views/Home.vue')
      }
    ]
  },
  {
    path: '/user',
    name: 'user',
    component: Main,
    meta: {
      hideInBread: true
    },
    children: [
      {
        path: 'userManage',
        name: 'userManage',
        meta: {
          icon: '_user-manage',
          title: '用户管理'
        },
        component: () => import('@/views/user/User.vue')
      }
    ]
  },
  {
    path: '/album',
    name: 'albums',
    meta: {
      icon: '_album',
      title: '专辑'
    },
    component: Main,
    children: [
      {
        path: 'albumManage',
        name: 'albumManage',
        meta: {
          icon: '_album-manage',
          title: '专辑管理'
        },
        component: () => import('@/views/album/Album.vue')
      },
      {
        path: 'albumTrackManage',
        name: 'albumTrackManage',
        meta: {
          title: '单集管理',
          hideInMenu: true,
          hideInBread: true,
        },
        component: () => import('@/views/album/track/AlbumTrack.vue')
      },
      {
        path: 'albumCategory',
        name: 'albumCategory',
        meta: {
          icon: '_category',
          title: '专辑分类'
        },
        component: () => import('@/views/albumCategory/AlbumCategory.vue')
      },
    ]
  },
]
