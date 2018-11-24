import { login, userPage, getUser, addUser, editUser, userRemoved,
  userExport, userImportTemplate, userImport } from '@/api/user'
import { setToken, getToken, setUserId, getUserId } from '@/libs/util'
const state = {
  userName: '',
  userId: getUserId(),
  token: getToken(),
}

const actions = {
  handleLogin ({ commit }, param) {
    return new Promise((resolve, reject) => {
      login(param).then(res => {
        console.log('login#response', res)
        const { fields } = res
        commit('setToken', fields.token)
        commit('setUserId', fields.userId)
        commit('setUserName', fields.userName)
        resolve(res)
      }).catch(error => {
        reject(error)
      })
    })
  },
  // 退出登录
  handleLogOut ({ commit }) {
    return new Promise((resolve) => {
      commit('setToken', '')
      commit('setUserId', '')
      resolve()
    })
  },

  userPage ({ commit }, param) {
    return new Promise((resolve, reject) => {
      userPage(param).then(res => {
        console.log('userPage#response', res)
        resolve(res)
      }).catch(error => {
        reject(error)
      })
    })
  },

  getUser ({ commit }, param) {
    return new Promise((resolve, reject) => {
      getUser(param).then(res => {
        console.log('getUser#response', res)
        resolve(res)
      }).catch(error => {
        reject(error)
      })
    })
  },

  userExport ({ commit }, param) {
    return new Promise((resolve, reject) => {
      userExport(param).then(res => {
        resolve(res)
      }).catch(error => {
        reject(error)
      })
    })
  },
  userRemoved ({ commit }, param) {
    return new Promise((resolve, reject) => {
      userRemoved(param).then(res => {
        console.log('userRemoved#response', res)
        resolve(res)
      }).catch(error => {
        reject(error)
      })
    })
  },
  editUser ({ commit }, param) {
    return new Promise((resolve, reject) => {
      editUser(param).then(res => {
        console.log('editUser#response', res)
        resolve(res)
      }).catch(error => {
        reject(error)
      })
    })
  },
  addUser ({ commit }, param) {
    return new Promise((resolve, reject) => {
      addUser(param).then(res => {
        console.log('editUser#response', res)
        resolve(res)
      }).catch(error => {
        reject(error)
      })
    })
  },
  userImportTemplate ({ commit }, param) {
    return new Promise((resolve, reject) => {
      userImportTemplate(param).then(res => {
        resolve(res)
      }).catch(error => {
        reject(error)
      })
    })
  },
  userImport ({ commit }, param) {
    return new Promise((resolve, reject) => {
      userImport(param).then(res => {
        resolve(res)
      }).catch(error => {
        reject(error)
      })
    })
  },
}

const mutations = {
  setUserId (state, id) {
    state.userId = id
    setUserId(id)
  },
  setUserName (state, name) {
    state.userName = name
  },
  setToken (state, token) {
    state.token = token
    setToken(token)
  },
}

export default {
  state,
  actions,
  mutations
}
