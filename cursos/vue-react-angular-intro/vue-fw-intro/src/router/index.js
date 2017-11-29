import Vue from 'vue'
import Router from 'vue-router'
import Hobbies from '@/components/Hobbies'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Hobbies',
      component: Hobbies
    }
  ]
})
