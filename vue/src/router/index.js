import Vue from 'vue'
import VueRouter from 'vue-router'
import login from '../views/login/login.vue'
import forgetPassword from "@/views/forgetPassword/forgetPassword";
import rootLogin from '@/views/rootLogin/rootLogin.vue';
Vue.use(VueRouter)

const routes = [

  {
    path: '/',
    name: 'login',
    component: login,
  },
  {
    path: '/rootLogin',
    name: 'rootLogin',
    component: rootLogin,
  },
  {
    path: '/forgetPassword',
    name: 'forgetPassword',
    component: forgetPassword
  }

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
