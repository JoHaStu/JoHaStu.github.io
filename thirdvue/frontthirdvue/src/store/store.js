import { createStore } from 'vuex';
import createPersistedState from "vuex-persistedstate";

export default createStore({
  state() {
    return {
      SessionUser: {},
      invItem: {},
      prodname: {},
      lotno: {}
    }
  },
  mutations: {
    setSessionUser(state, value) {
      state.SessionUser = value
    },
    setInvItem(state, value) {
      state.invItem = value
    },
    setProdname(state, value) {
      state.prodname = value
     },
     setLotno(state, value) {
      state.lotno = value
  },
},

  plugins: [createPersistedState()],
})