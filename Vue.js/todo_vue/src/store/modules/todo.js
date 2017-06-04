import * as types from '../mutation-types'

// initial state
const state = {
  all: [],
  new: '111'
}

// getters
const getters = {
  allTodos: state => state.all,
  newTodo: state => state.new
}

// actions
const actions = {
  getAllTodos ({commit}) {
    commit(types.RECEIVE_TODOS, {todos: ['todo1', 'todo2', 'todo3']})
  },
  setNewTodo ({commit}, todoTitle) {
    commit(types.RECEIVE_NEWTODO, todoTitle)
  }
}

// mutations
const mutations = {
  [types.RECEIVE_TODOS] (state, {todos}) {
    state.all = todos
  },
  [types.RECEIVE_NEWTODO] (state, newTodo) {
    state.new = newTodo
    console.log('state.new is ' + state.new)
  }
}

export default {
  state,
  getters,
  actions,
  mutations
}
