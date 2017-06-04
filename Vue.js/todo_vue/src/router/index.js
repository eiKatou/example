import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import Top from '@/components/Top'
import New from '@/components/New'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/hello',
      name: 'Hello',
      component: Hello
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
    }
  ]
})
