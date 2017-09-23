<template>
  <div>
    <h1>Login</h1>
    <input type="text" name="email" size="30" maxlength="20" v-model="email"><br>
    <input type="text" name="password" size="30" maxlength="20" v-model="password"><br><br>
    <input type="button" value="ログインする" v-on:click="loginButtonClick()">
  </div>
</template>

<script>
import firebase from 'firebase'

export default {
  name: 'login',
  data: function () {
    return {
      email: '',
      password: ''
    }
  },
  methods: {
    loginButtonClick: function () {
      console.log('login button click')
      firebase.auth().createUserWithEmailAndPassword(this.email, this.password)
    }
  },
  computed: {
  },
  created () {
  }
}
</script>

<style scoped>
</style>
