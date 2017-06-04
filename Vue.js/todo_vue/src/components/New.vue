<template>
  <div>
    <h1>新しいTODO</h1>
    <input type="text" name="title" size="30" maxlength="20" v-model="todo"><br><br>
    <input type="button" value="作成する" v-on:click="buttonClick">
  </div>
</template>

<script>
// import * as types from '../store/mutation-types'
import { mapGetters, mapActions } from 'vuex'

export default {
  name: 'new',
  methods: {
    ...mapActions([
    ]),
    buttonClick: function () {
      console.log('new button click')
      console.log('new todo is ' + this.$store.state.new)
      // store.setTodo(this.title, this.detail)
      // router.push('/newConfirm');
    }
  },
  computed: mapGetters({
    todo: 'newTodo'
  }),
  // computed: {
  //   message: {
  //     get () {
  //       return this.$store.state.message
  //       // return '1222'
  //     },
  //     set (value) {
  //       this.$store.commit(types.RECEIVE_NEWTODO, value)
  //     }
  //   }
  // },
  created () {
    this.$store.dispatch('setNewTodo', '')
  }
}
</script>

<style scoped>
</style>
