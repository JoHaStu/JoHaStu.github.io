import { createStore } from 'vuex';
import createPersistedState from "vuex-persistedstate";

export default createStore({
  state() {
    return {
      SessionUser: {},
      boardItem: {},
      commItem: {}
    }
  },
  mutations: {
    setSessionUser(state, value) {
      state.SessionUser = value
    },
    setBoardItem(state, value) {
      state.boardItem = value
    },
    setCommItem(state, value) {
      state.commItem = value
    }
  },

  plugins: [createPersistedState()],
})