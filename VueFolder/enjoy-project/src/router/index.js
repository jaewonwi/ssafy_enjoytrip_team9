import { createRouter, createWebHistory } from 'vue-router'
import MainPage from '@/views/MainPage.vue'
import BoardPage from '@/views/BoardPage.vue'

import RegistPage from '@/views/user/RegistPage.vue'
import LoginPage from '@/views/user/LoginPage.vue'
import MyPage from '@/views/user/MyPage.vue'

import MapPage from '@/views/map/MapPage.vue'

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
      path: '/mypage',
      component: MyPage
    },
    {
      path: '/board',
      component: BoardPage,
      // children: [
      //   {path: '/board/editor', BoardEdior}
      // ]

    },
    {
      path: '/map',
      component: MapPage,
    }
  ]
})

export default router
