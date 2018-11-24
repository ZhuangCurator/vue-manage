import { albumPage, album, albumAdd, albumStatus, albumEdit} from '@/api/album'

const state = {
}

const actions = {
  albumPage ({ commit }, param) {
    return new Promise((resolve, reject) => {
      albumPage(param).then(res => {
        console.log('albumPage#response', res)
        resolve(res)
      }).catch(error => {
        reject(error)
      })
    })
  },
  album ({ commit }, param) {
    return new Promise((resolve, reject) => {
      album(param).then(res => {
        console.log('album#response', res)
        resolve(res)
      }).catch(error => {
        reject(error)
      })
    })
  },
  albumAdd ({ commit }, param) {
    return new Promise((resolve, reject) => {
      albumAdd(param).then(res => {
        console.log('albumAdd#response', res)
        resolve(res)
      }).catch(error => {
        reject(error)
      })
    })
  },
  albumEdit ({ commit }, param) {
    return new Promise((resolve, reject) => {
      albumEdit(param).then(res => {
        console.log('albumEdit#response', res)
        resolve(res)
      }).catch(error => {
        reject(error)
      })
    })
  },
  albumStatus ({ commit }, param) {
    return new Promise((resolve, reject) => {
      albumStatus(param).then(res => {
        console.log('albumStatus#response', res)
        resolve(res)
      }).catch(error => {
        reject(error)
      })
    })
  }
}

const mutations = {
}

export default {
  state,
  actions,
  mutations
}
