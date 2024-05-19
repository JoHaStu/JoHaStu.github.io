import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/list',
    name: 'ListView',
    component: () => import(/* webpackChunkName: "user" */ '../views/ListView.vue')
  },
  {
    path: '/getAllCombined',
    name: 'AllView',
    component: () => import(/* webpackChunkName: "user" */ '../views/AllView.vue')
  },
  {
    path: '/updateMst',
    name: 'UpdateMstView',
    component: () => import(/* webpackChunkName: "user" */ '../views/UpdateMstView.vue')
  },
  {
    path: '/updateDtl',
    name: 'UpdateDtl',
    component: () => import(/* webpackChunkName: "user" */ '../views/UpdateDtl.vue')
  },
  {
    path: '/insertMst',
    name: 'InsertMstView',
    component: () => import(/* webpackChunkName: "user" */ '../views/InsertMstView.vue')
  },
  {
    path: '/insertDtl',
    name: 'InsertDtlView',
    component: () => import(/* webpackChunkName: "user" */ '../views/InsertDtlView.vue')
  }

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router