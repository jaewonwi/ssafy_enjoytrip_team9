import { createRouter, createWebHistory } from 'vue-router'
import RegistPage from '@/components/user/RegistPage.vue'
import LoginPage from '@/components/user/LoginPage.vue'
import MainPage from '@/components/MainPage.vue'
import BoardPage from '@/components/board/BoardPage.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      component: MainPage
    },
    {
      path: '/regist',
      component: RegistPage
    },
    {
      path: '/login',
      component: LoginPage
    },
    {
      path: '/board',
      component: BoardPage
    },
  ]
})

export default router
