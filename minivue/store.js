import { createStore } from 'vuex'

export default createStore({
  state() {
    return {
      mstItem: {},
      dtlItem: {}
    }
  },
  mutations: {
    setMstItem(state, value) {
      state.mstItem = value
    },
    setDtlItem(state, value) {
      state.dtlItem = value
    }
  }
})

/*import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex);

export const store = new Vuex.Store({
  state: {
   return {
    
    }
  },
  mutations: {
    
  }
  })


export default store;*/