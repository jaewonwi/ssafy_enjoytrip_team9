import { createRouter, createWebHistory } from 'vue-router'
import MainPage from '@/views/MainPage.vue'
import BoardPage from '@/views/BoardPage.vue'
import SearchPage from '@/views/SearchPage.vue'

import RegistPage from '@/views/user/RegistPage.vue'
import LoginPage from '@/views/user/LoginPage.vue'
import MyPage from '@/views/user/MyPage.vue'

import BoardList from '@/components/board/BoardList.vue'
import BoardDetail from '@/components/board/BoardDetail.vue'
import BoardEditor from '@/components/board/BoardEditor.vue'

import MapPage from '@/views/map/MapPage.vue'

import AttractionSearch from '@/components/attraction/AttractionSearch.vue'


// import { useAuthStore } from '@/stores/authStore'

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
      name: 'Board',
      path: '/board',
      component: BoardPage,
      // beforeEnter: (to, from, next) => {
      //   const { authStore } = useAuthStore()

      //   let isLogin = sessionStorage.getItem('isLogin')
      //   if (authStore.isLogin || isLogin == 'true'){
      //     return next();
      //   } else {
      //     next('/login');
      //   }
      
      // jaewon
      children: [
        {path: '', name: 'BoardList', component: BoardList},
        {path: 'detail/:boardId', name: 'BoardDetail', component: BoardDetail},
        {path: 'editor/:boardId*', name: 'BoardEditor', component: BoardEditor},
      ]
    },
    {
      path: '/map',
      component: MapPage,
    },
    {
      path: '/search',
      component: SearchPage,
      children: [
        {path: '', name: 'AttactionSearch', component: AttractionSearch},
        // {path: 'detail/:boardId', name: 'BoardDetail', component: BoardDetail},
        // {path: 'editor/:boardId*', name: 'BoardEditor', component: BoardEditor},
      ]
    }
  ]
})

export default router
