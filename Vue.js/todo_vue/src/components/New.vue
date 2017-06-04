<template>
  <div>
    <h1>新しいTODO</h1>
    <input type="text" name="title" size="30" maxlength="20" v-model="todo"><br><br>
    <input type="button" value="作成する" v-on:click="buttonClick">
  </div>
</template>

<script>
import * as types from '../store/mutation-types'

export default {
  name: 'new',
  methods: {
    buttonClick: function () {
      console.log('new button click')
      console.log('new todo is ' + this.$store.getters.newTodo)
      // router.push('/newConfirm');
    }
  },
  computed: {
    todo: {
      get () {
        return this.$store.getters.newTodo
      },
      set (value) {
        this.$store.commit(types.RECEIVE_NEWTODO, value)
      }
    }
  },
  created () {
    // this.$store.dispatch('setNewTodo', '')
  }
}
</script>

<style scoped>
</style>
