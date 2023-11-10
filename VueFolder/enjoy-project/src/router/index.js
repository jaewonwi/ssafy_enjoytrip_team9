import { createRouter, createWebHistory } from 'vue-router'
import MainPage from '@/components/MainPage.vue'
import RegistPage from '@/components/user/RegistPage.vue'
import LoginPage from '@/components/user/LoginPage.vue'
import BoardPage from '@/components/board/BoardPage.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/main',
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
      component: BoardPage,
      children: [
        
      ]
    },
  ]
})

export default router
