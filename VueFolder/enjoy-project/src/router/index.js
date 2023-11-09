import { createRouter, createWebHistory } from 'vue-router'
import RegistPage from '@/components/user/RegistPage.vue'
import LoginPage from '@/components/user/LoginPage.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/regist',
      component: RegistPage
    },
    {
      path: '/login',
      component: LoginPage
    },
  ]
})

export default router
