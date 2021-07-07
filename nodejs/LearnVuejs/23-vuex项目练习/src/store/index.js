import Vue from 'vue'
import Vuex from 'vuex'

import getters from './getters'
import mutations from './mutations'

Vue.use(Vuex)

const state = {
  tasks: [],
  showIndex: 0
}

const store = new Vuex.Store({
  state,
  mutations,
  getters
})

export default store
