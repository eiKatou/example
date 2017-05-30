<template>
  <div id="app">
    <img src="./assets/logo.png">
    <router-view
      v-bind:todos="todos"
      v-on:add-new-todo='addNewTodo'
      v-on:edit-new-todo='editNewTodo'
    ></router-view>
  </div>
</template>

<script>
export default {
  name: 'app',
  data () {
    return {
      todos: [ 'todo1', 'todo2', 'todo3' ]
    }
  },
  methods: {
    addNewTodo: function (t, d) {
      console.log('addNewTodo ' + t + ':' + d)
      this.todos.push(t)
    },
    editNewTodo: function (index, title) {
      console.log('editNewTodo ' + index + ':' + title)
      this.todos[index] = title
    }
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
