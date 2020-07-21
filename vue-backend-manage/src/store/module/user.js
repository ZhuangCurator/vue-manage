import { login, userPage, getUser, addUser, editUser, userRemoved,
  userExport, userImportTemplate, userImport ,imageCode} from '@/api/user'
import { setToken, getToken, setUsername, getUsername } from '@/libs/util'
const state = {
  userName: getUsername(),
  token: getToken(),
}

const actions = {
  handleLogin ({ commit }, param) {
    return new Promise((resolve, reject) => {
      login(param).then(data => {
        console.log('login#response#data', data)
        const { fields } = data
        console.log('login#fields', fields)
        commit('setToken', fields.token)
        commit('setUserName', fields.username)
        resolve(data)
      }).catch(error => {
        reject(error)
      })
    })
  },
  handleImageCode ({ commit }, param) {
    return new Promise((resolve, reject) => {
      imageCode(param).then(data => {
        console.log('imageCode#response#data', data)
        resolve(data)
      }).catch(error => {
        reject(error)
      })
    })
  },
  // 退出登录
  handleLogOut ({ commit }) {
    return new Promise((resolve) => {
      commit('setToken', '')
      commit('setUsername', '')
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
  setUsername (state, name) {
    state.userName = name
    setUsername(name)
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
