import * as types from '../mutation-types'

// initial state
const state = {
  all: ['買い物に行く', '宿題をする'],
  new: ''
}

// getters
const getters = {
  allTodos: state => state.all,
  newTodo: state => state.new,
  todoByIndex: state => index => {
    return state.all[index]
  }
}

// actions
const actions = {
  getAllTodos ({commit}) {
    commit(types.RECEIVE_TODOS, {todos: ['todo1', 'todo2', 'todo3']})
  },
  setNewTodo ({commit}, todoTitle) {
    commit(types.RECEIVE_NEWTODO, todoTitle)
  },
  addNewTodo ({commit}, todo) {
    commit(types.RECEIVE_ADDNEWTODO, todo)
  },
  setTodo ({commit}, todo, index) {
    commit(types.RECEIVE_SETTODO, todo, index)
  }
}

// mutations
const mutations = {
  [types.RECEIVE_TODOS] (state, {todos}) {
    state.all = todos
  },
  [types.RECEIVE_NEWTODO] (state, newTodo) {
    state.new = newTodo
  },
  [types.RECEIVE_ADDNEWTODO] (state, newTodo) {
    state.all.push(newTodo)
  },
  [types.RECEIVE_SETTODO] (state, todo, index) {
    state.all.splice(index, 1, todo)
  }
}

export default {
  state,
  getters,
  actions,
  mutations
}
