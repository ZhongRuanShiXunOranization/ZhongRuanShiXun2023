import Vue from 'vue'
import VueRouter from 'vue-router'
//import HomeView from '../views/HomeView.vue'
import layout from "@/views/LayOut/layout";

Vue.use(VueRouter)

const routes = [
  // {
  //   path: '/home',
  //   name: 'home',
  //   component: HomeView
  // },
  // {
  //   path: '/about',
  //   name: 'about',
  //   // route level code-splitting
  //   // this generates a separate chunk (about.[hash].js) for this route
  //   // which is lazy-loaded when the route is visited.
  //   component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  // },
  {
    path: '/',
    name: 'register',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import('../views/pages/register.vue'),
    //redirect:"/register"
  },
  {
    path: '/layout',
    name:'layout',
    component: layout,
    redirect:"/home",
    children:[
      {
        path:'/home',
        name:'home',
        component:()=>import("../views/pages/home.vue")
      },
      {
        path: '/settings',
        name:'settings',
        component:()=>import("../views/pages/settings.vue")
      },
      {
        path: '/historyPage',
        name:'historyPage',
        component:()=>import("../views/pages/historyPage")
      }
    ]
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
