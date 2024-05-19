import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store/store'


createApp(App).use(router).use(store).mount('#app')

/*import { createApp } from 'vue'
import store from './scripts/store'
import App from './App.vue'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.min.js'
import {createRouter, createWebHistory} from 'vue-router'
import HomeList from '@/components/HomeList.vue'
import InsertMst from '@/components/InsertMst.vue'


store;

const routes = [
    {path:'/', name: home, component: HomeList},
    {path:'/insert', name: insert, component: InsertMst},
]

const router = createRouter({
    history: createWebHistory(),
    routes: routes
})

createApp(App).use(store).use(router).mount('#app')*/
