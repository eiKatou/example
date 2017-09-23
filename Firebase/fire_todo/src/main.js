// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import store from './store'
import firebase from 'firebase'

var config = {
  apiKey: 'AIzaSyAVHtkJDx8E342I_CdHJdrFVQGMuJxQub0',
  authDomain: 'todo-63ab1.firebaseapp.com',
  databaseURL: 'https://todo-63ab1.firebaseio.com',
  projectId: 'todo-63ab1',
  storageBucket: 'todo-63ab1.appspot.com',
  messagingSenderId: '226663718159'
}
firebase.initializeApp(config)
Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  store,
  router,
  template: '<App/>',
  components: { App }
})
