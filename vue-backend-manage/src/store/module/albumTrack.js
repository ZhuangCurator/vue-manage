import { albumTrackPage, albumTrackAdd, albumTrackStatus, albumTrackBatchStatus, albumTrackEdit } from '@/api/albumTrack'

const state = {
}

const actions = {
  albumTrackPage ({ commit }, param) {
    return new Promise((resolve, reject) => {
      albumTrackPage(param).then(res => {
        console.log('albumTrackPage#response', res)
        resolve(res)
      }).catch(error => {
        reject(error)
      })
    })
  },
  albumTrackAdd ({ commit }, param) {
    return new Promise((resolve, reject) => {
      albumTrackAdd(param).then(res => {
        console.log('albumTrackAdd#response', res)
        resolve(res)
      }).catch(error => {
        reject(error)
      })
    })
  },
  albumTrackEdit ({ commit }, param) {
    return new Promise((resolve, reject) => {
      albumTrackEdit(param).then(res => {
        console.log('albumTrackEdit#response', res)
        resolve(res)
      }).catch(error => {
        reject(error)
      })
    })
  },
  albumTrackStatus ({ commit }, param) {
    return new Promise((resolve, reject) => {
      albumTrackStatus(param).then(res => {
        console.log('albumTrackStatus#response', res)
        resolve(res)
      }).catch(error => {
        reject(error)
      })
    })
  },
  albumTrackBatchStatus ({ commit }, param) {
    return new Promise((resolve, reject) => {
      albumTrackBatchStatus(param).then(res => {
        console.log('albumTrackBatchStatus#response', res)
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
