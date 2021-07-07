import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Button from '../views/Button.vue'
import ButtonDetails from '../views/ButtonDetails.vue'
import Link from '../views/Link.vue'
import Layout from '../views/Layout.vue'
import Container from '../views/Container.vue'
import Radio from '../views/Radio.vue'
import Checkbox from '../views/Checkbox.vue'
import Input from '../views/Input.vue'

Vue.use(VueRouter)

const routes = [
  {path: '/', name: 'Home', component: Home},
  {path: '/button', name: 'Button', component: Button},
  {path: '/buttondetails', name: 'ButtonDetails', component: ButtonDetails},
  {path: '/link', name: 'Link', component: Link},
  {path: '/layout', name: 'Layout', component: Layout},
  {path: '/container', name: 'Container', component: Container},
  {path: '/radio', name: 'Radio', component: Radio},
  {path: '/checkbox', name: 'Checkbox', component: Checkbox},
  {path: '/input', name: 'Input', component: Input},
  {path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
