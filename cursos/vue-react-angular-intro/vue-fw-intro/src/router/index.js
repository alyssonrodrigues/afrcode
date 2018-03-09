import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Hobbies from '@/components/Hobbies'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Hobbies',
      component: Hobbies
    },
    {
      path: '/helloworld',
      name: 'HelloWorld',
      component: HelloWorld
    }
  ]
})
