import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/login',
    name: 'LoginForm',
    component: () => import(/* webpackChunkName: "user" */ '../views/LoginForm.vue')
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
    path: '/detailview',
    name: 'DetailView',
    component: () => import(/* webpackChunkName: "user" */ '../views/DetailView.vue')
  },
  {
    path: '/updateBoard',
    name: 'UpdateBoard',
    component: () => import(/* webpackChunkName: "user" */ '../views/UpdateBoard.vue')
  },
  {
    path: '/updateDtl',
    name: 'UpdateDtl',
    component: () => import(/* webpackChunkName: "user" */ '../views/UpdateDtl.vue')
  },
  {
    path: '/addComment',
    name: 'AddComment',
    component: () => import(/* webpackChunkName: "user" */ '../views/AddComment.vue')
  },
  {
    path: '/addPost',
    name: 'AddPost',
    component: () => import(/* webpackChunkName: "user" */ '../views/AddPost.vue')
  },
  /*{
    path: '/logout',
    name: 'HomeView',
    component: () => import(/* webpackChunkName: "user"  '../views/HomeView.vue')
  },*/
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router