import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '@/components/Home'
import Shop from '@/components/Shop'

Vue.use(VueRouter)

export default new VueRouter({
  routes: [
    { path: '/', redirect: '/Home' },
    {
      path: '/Home',
      name: 'Home',
      component: Home
    },
    {
      path: '/Shop',
      name: 'Shop',
      component: Shop
    }
  ]
})
