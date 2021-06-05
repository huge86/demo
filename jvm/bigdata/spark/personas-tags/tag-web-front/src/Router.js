import Vue from 'vue'
import Router from 'vue-router'

import TagPage from './views/tag/TagPage'
import TagFourPage from './views/tag/TagFourPage'
import TagFivePage from './views/tag/TagFivePage'
import TaskPage from './views/task/TaskPage'

const routes = [
  {path:'/',redirect:'/tags'},
  {
    path: "/tags",
    component: TagPage,
    children: [
      {
        path: 'four/:pid',
        component: TagFourPage
      },
      {
        path: 'five/:pid',
        component: TagFivePage
      }
    ]
  },
  {
    path: "/tasks",
    component: TaskPage
  }
]

Vue.use(Router)

export default new Router({
    scrollBehavior: () => ({ y: 0 }),
    routes: routes
})