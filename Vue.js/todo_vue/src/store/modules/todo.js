import * as types from '../mutation-types'

// initial state
const state = {
  all: ['todoA', 'todoB', 'todoC']
}

// getters
const getters = {
  allTodos: state => state.all
}

// actions
const actions = {
  getAllTodos ({commit}) {
    commit(types.RECEIVE_TODOS, {todos: ['todo1', 'todo2', 'todo3']})
  }
}

// mutations
const mutations = {
  [types.RECEIVE_TODOS] (state, {todos}) {
    state.all = todos
  }
}

export default {
  state,
  getters,
  actions,
  mutations
}
