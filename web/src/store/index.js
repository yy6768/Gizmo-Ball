import Vue from 'vue'
import Vuex from 'vuex'
import ModuleLayout from './layout'
import ModuleIcon from './icon'
Vue.use(Vuex)

export default new Vuex.Store({
  state: {
  },
  getters: {
  },
  mutations: {

  },
  actions: {
    
  },
  modules: {
    layout : ModuleLayout,
    icon: ModuleIcon
  }
})
