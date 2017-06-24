<template>
  <div>
    <h1>TODO一覧</h1>
    <div id="todo_list">
      <table id="todo_table">
      <template v-for="(item, index) in todos" >
        <tr class="todo_row">
          <td class="todo">{{ item }}</td>
          <td><router-link :to="{ name:'edit', params:{index:index} }">edit</router-link></td>
        </tr>
      </template>
      </table>
    </div>
    <br>
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
    // this.$store.dispatch('getAllTodos')
  }
}
</script>

<style scoped>
#todo_list {
  width: 40%;
  margin: auto;
}
#todo_table {
  width: 100%;
  border-collapse:collapse;
}
.todo_row {
  border-bottom:1.5px #aaa solid;
}
.todo {
  text-align: left;
}
.edit_link {
}
.router-link-active {
}
</style>
