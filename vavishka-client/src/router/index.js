import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '@/components/Home'
import Shop from '@/components/Shop'
import SampleSearch from '@/components/SampleSearch'

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
    },
    {
      path: '/sample-search',
      name: 'SampleSearch',
      component: SampleSearch
    }
  ]
})
