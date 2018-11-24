import Vue from 'vue'
import Vuex from 'vuex'
import state from './state'
import actions from './actions'
import mutations from './mutations'
import getters from './getters'
import user from './module/user'
import album from './module/album'
import albumCategory from './module/albumCategory'
import albumTrack from './module/albumTrack'
import upload from './module/upload'
import ad from './module/ad'
import app from './module/app'
Vue.use(Vuex)

export default new Vuex.Store({
  state,
  actions,
  mutations,
  getters,
  modules: {
    user,
    album,
    albumCategory,
    albumTrack,
    upload,
    ad,
    app
  }
})
