import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import Login from '@/components/Login'
import Top from '@/components/Top'
import New from '@/components/New'
import NewConfirm from '@/components/NewConfirm'
import Edit from '@/components/Edit'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/hello',
      name: 'Hello',
      component: Hello
    },
    {
      path: '/login',
      name: 'login',
      component: Login
    },
    {
      path: '/',
      name: 'top',
      component: Top
    },
    {
      path: '/new',
      name: 'new',
      component: New
    },
    {
      path: '/newConfirm',
      name: 'newConfirm',
      component: NewConfirm
    },
    {
      path: '/edit/:index',
      name: 'edit',
      component: Edit
    }
  ]
})
