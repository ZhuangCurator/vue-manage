import { adPositionPage, adPosition, adPositionStatus, adType, adAdd, adEdit,
  adContentPage, adContentStatus, adContentParam, adContent, adContentEdit, adContentAdd } from '@/api/ad'

const state = {
}

const actions = {
  adPositionPage ({ commit }, param) {
    return new Promise((resolve, reject) => {
      adPositionPage(param).then(res => {
        console.log('adPositionPage#response', res)
        resolve(res)
      }).catch(error => {
        reject(error)
      })
    })
  },

  adPosition ({ commit }, param) {
    return new Promise((resolve, reject) => {
      adPosition(param).then(res => {
        console.log('adPosition#response', res)
        resolve(res)
      }).catch(error => {
        reject(error)
      })
    })
  },
  adType () {
    return new Promise((resolve, reject) => {
      adType().then(res => {
        console.log('adType#response', res)
        resolve(res)
      }).catch(error => {
        reject(error)
      })
    })
  },
  adAdd ({ commit }, param) {
    return new Promise((resolve, reject) => {
      adAdd(param).then(res => {
        console.log('adAdd#response', res)
        resolve(res)
      }).catch(error => {
        reject(error)
      })
    })
  },
  adEdit ({ commit }, param) {
    return new Promise((resolve, reject) => {
      adEdit(param).then(res => {
        console.log('adEdit#response', res)
        resolve(res)
      }).catch(error => {
        reject(error)
      })
    })
  },
  adPositionStatus ({ commit }, param) {
    return new Promise((resolve, reject) => {
      adPositionStatus(param).then(res => {
        console.log('adPositionStatus#response', res)
        resolve(res)
      }).catch(error => {
        reject(error)
      })
    })
  },
  adContentPage ({ commit }, param) {
    return new Promise((resolve, reject) => {
      adContentPage(param).then(res => {
        console.log('adContentPage#response', res)
        resolve(res)
      }).catch(error => {
        reject(error)
      })
    })
  },
  adContentStatus ({ commit }, param) {
    return new Promise((resolve, reject) => {
      adContentStatus(param).then(res => {
        console.log('adContentStatus#response', res)
        resolve(res)
      }).catch(error => {
        reject(error)
      })
    })
  },
  adContentParam () {
    return new Promise((resolve, reject) => {
      adContentParam().then(res => {
        console.log('adContentParam#response', res)
        resolve(res)
      }).catch(error => {
        reject(error)
      })
    })
  },
  adContent ({ commit }, param) {
    return new Promise((resolve, reject) => {
      adContent(param).then(res => {
        console.log('adContent#response', res)
        resolve(res)
      }).catch(error => {
        reject(error)
      })
    })
  },
  adContentEdit ({ commit }, param) {
    return new Promise((resolve, reject) => {
      adContentEdit(param).then(res => {
        console.log('adContentEdit#response', res)
        resolve(res)
      }).catch(error => {
        reject(error)
      })
    })
  },
  adContentAdd ({ commit }, param) {
    return new Promise((resolve, reject) => {
      adContentAdd(param).then(res => {
        console.log('adContentAdd#response', res)
        resolve(res)
      }).catch(error => {
        reject(error)
      })
    })
  },
}

const mutations = {
}

export default {
  state,
  actions,
  mutations
}
