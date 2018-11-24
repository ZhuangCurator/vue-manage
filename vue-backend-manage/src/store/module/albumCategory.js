import { albumCategoryList, albumCategoryStatus, albumCategoryEdit, albumCategoryAdd} from '@/api/albumCategory'

const state = {
}

const actions = {
  albumCategoryList ({ commit }, param) {
    return new Promise((resolve, reject) => {
      albumCategoryList(param).then(res => {
        console.log('albumCategoryList#response', res)
        resolve(res)
      }).catch(error => {
        reject(error)
      })
    })
  },
  albumCategoryAdd ({ commit }, param) {
    return new Promise((resolve, reject) => {
      albumCategoryAdd(param).then(res => {
        console.log('albumCategoryAdd#response', res)
        resolve(res)
      }).catch(error => {
        reject(error)
      })
    })
  },
  albumCategoryEdit ({ commit }, param) {
    return new Promise((resolve, reject) => {
      albumCategoryEdit(param).then(res => {
        console.log('albumCategoryEdit#response', res)
        resolve(res)
      }).catch(error => {
        reject(error)
      })
    })
  },
  albumCategoryStatus ({ commit }, param) {
    return new Promise((resolve, reject) => {
      albumCategoryStatus(param).then(res => {
        console.log('albumCategoryStatus#response', res)
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
