import { createRouter, createWebHistory } from 'vue-router'
import HomeLogin from '../views/HomeLogin.vue'

const routes = [
  {
    path: '/',
    name: 'HomeLogin',
    component: HomeLogin
  },
  {
    path: '/register',
    name: 'RegisterForm',
    component: () => import(/* webpackChunkName: "user" */ '../views/RegisterForm.vue')
  },
  {
    path: '/session',
    name: 'SessionHomeView',
    component: () => import(/* webpackChunkName: "user" */ '../views/SessionHomeView.vue')
  },
  {
    path: '/update',
    name: 'UpdateForm',
    component: () => import(/* webpackChunkName: "user" */ '../views/UpdateForm.vue')
  },
  {
    path: '/insert',
    name: 'InsertProduct',
    component: () => import(/* webpackChunkName: "user" */ '../views/InsertProduct.vue'),
    // children: [
    //   {
    //   path: '/popup',
    //   name: 'PopupCategories',
    //   component: () => import(/* webpackChunkName: "user" */ '../views/PopupCategories.vue')
    //   }]
  },
  {
    path: '/popup',
    name: 'PopupCategories',
    component: () => import(/* webpackChunkName: "user" */ '../views/PopupCategories.vue')
    }

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router