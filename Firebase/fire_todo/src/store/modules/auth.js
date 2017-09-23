import firebase from 'firebase'

const auth = {
  namespaced: true,
  state: {
    user: {}
  },
  getters: {
    user: (state) => state.user
  },
  mutations: {
    set(state, payload) {
      state.user = payload
    }
  },
  actions: {
    init({ commit }) {
      return new Promise((resolve) => {
        firebase.auth().onAuthStateChanged((user) => {
          let profile = {
            auth: false,
            uid: '',
            email: ''
          }
          if (user) {
            profile = {
              auth: true,
              uid: user.uid,
              email: user.email
            }
          }
          commit('set', profile)
          resolve()
        })
      })
    }
  }
}

export default auth