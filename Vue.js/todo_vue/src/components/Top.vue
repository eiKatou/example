<template>
  <div>
    <h1>TODO一覧</h1>
    <template v-for="(item, index) in todos" >
      <span>
        {{ item }}  
        <!-- <router-link :to="{ name:'edit', params:{item:index} }">edit</router-link> --><br/>
      </span>
    </template>
    <input type="button" value="新規作成" @click="goNewTask()">
  </div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'

export default {
  name: 'top',
  methods: {
    ...mapActions([
    ]),
    goNewTask: function () {
      this.$router.push('new')
    }
  },
  computed: mapGetters({
    todos: 'allTodos'
  }),
  created () {
    this.$store.dispatch('getAllTodos')
  }
}
</script>

<style scoped>
</style>
