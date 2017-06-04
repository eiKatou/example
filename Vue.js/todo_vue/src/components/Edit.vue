<template>
  <div>
    <h1>TODOの編集</h1>
    タスク名<br>
    <input type="text" name="title" size="30" maxlength="20" v-model="editTodo"><br><br>
    <input type="button" value="編集完了する" v-on:click="okButtonClick()">
  </div>
</template>

<script>
// import * as types from '../store/mutation-types'
import { mapGetters, mapActions } from 'vuex'
// import { mapGetters } from 'vuex'

export default {
  name: 'edit',
  data: function () {
    return {
      editTodo: ''
    }
  },
  methods: {
    ...mapActions([
      'setTodo'
    ]),
    okButtonClick: function () {
      console.log('ok button click')
      let index = this.$router.currentRoute.params.item
      this.setTodo(this.editTodo, index)
      this.$router.push('/')
    }
  },
  computed: {
    ...mapGetters([
      'todoByIndex'
    ])
  },
  created () {
    let index = this.$router.currentRoute.params.index
    this.editTodo = this.todoByIndex(index)
  }
}
</script>

<style scoped>
</style>
