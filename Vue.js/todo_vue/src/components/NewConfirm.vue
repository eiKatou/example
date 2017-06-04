<template>
  <div>
    <h1>新しいTODOの確認</h1>
    <p>
      タスク名：<span>{{ newTodo }}</span><br>
    <p>
    <p>本当にこのタスクを作成しますか？<br>
      <input type="button" value="OK" v-on:click="okButtonClick()">
      <input type="button" value="戻る" v-on:click="cancelButtonClick()">
    </p>
  </div>
</template>

<script>
// import * as types from '../store/mutation-types'
import { mapGetters, mapActions } from 'vuex'
// import { mapGetters } from 'vuex'

export default {
  name: 'new',
  methods: {
    ...mapActions([
      'setNewTodo',
      'addNewTodo'
    ]),
    okButtonClick: function () {
      console.log('ok button click')
      this.addNewTodo(this.$store.getters.newTodo)
      this.setNewTodo('')
      this.$router.push('/')
    },
    cancelButtonClick: function () {
      console.log('cancel button click')
      this.$router.go(-1) // 1つ戻る
    }
  },
  computed: {
    ...mapGetters([
      'newTodo'
    ])
  },
  created () {
  }
}
</script>

<style scoped>
</style>
