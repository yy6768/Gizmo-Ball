import Vue from 'vue'
import VueRouter from 'vue-router'
import LayoutView from '@/views/LayoutView'
Vue.use(VueRouter)

const routes = [
  {
    path:'/',
    name: 'home',
    redirect: '/layout/',
  },
  {
    path:'/layout/',
    name:'index',
    component:LayoutView
  }
]

const router = new VueRouter({
  mode: 'history',
  routes
})

export default router
