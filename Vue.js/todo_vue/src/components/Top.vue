<template>
  <div>
    <h1>TODO一覧</h1>
    <template v-for="(item, index) in todos" >
      <span>
        {{ item }}  
        <router-link :to="{ name:'edit', params:{item:index} }">edit</router-link><br/>
      </span>
    </template>
    <input type="button" value="新規作成" v-on:click="goNewTask()">
  </div>
</template>

<script>
export default {
  name: 'top',
  props: ['todos'],
  methods: {
    goNewTask: function () {
      // store.clearTodo();
      // this.router.push('/new')
      this.$router.push('Hello')
    }
  }
}
</script>

<style scoped>
</style>
