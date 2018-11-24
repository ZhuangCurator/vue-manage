import { uploadImage, uploadMedia } from '@/api/fileUpload'

const state = {
}

const actions = {
  uploadImage ({ commit }, param) {
    return new Promise((resolve, reject) => {
      uploadImage(param).then(res => {
        console.log('uploadImage#response', res)
        resolve(res)
      }).catch(error => {
        reject(error)
      })
    })
  },
  uploadMedia ({ commit }, param) {
    return new Promise((resolve, reject) => {
      uploadMedia(param).then(res => {
        console.log('uploadMedia#response', res)
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
