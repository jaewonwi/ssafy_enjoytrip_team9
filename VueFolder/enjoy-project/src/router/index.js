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
import MemberManager from '@/views/user/MemberManagerPage.vue'

import SearchList from '@/components/search/SearchList.vue'
import SearchDetail from '@/components/search/SearchDetail.vue'
import { useLoginStore } from '@/stores/loginStore'

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
      component: MyPage,
      beforeEnter: (to, from, next) => {
        console.log("before enter")
        const { loginStore } = useLoginStore()
        let isLogin = sessionStorage.getItem('isLogin')
        if ((isLogin || loginStore.isLogin == true) && loginStore.userClsf == '002') {
          return next();
        } else {
          console.log("before enter: 통과못함")
          next('/login');
        }
      }
    },
    {
      name: 'Board',
      path: '/board',
      component: BoardPage,
      beforeEnter: (to, from, next) => {
        console.log("before enter")
        const { loginStore } = useLoginStore()

        let isLogin = sessionStorage.getItem('isLogin')
        if (isLogin || loginStore.isLogin == true) {
          return next();
        } else {
          console.log("before enter: 통과못함")
          next('/login');
        }
      },
      
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
        {path: '', name: 'SearchList', component: SearchList},
        {path: 'detail/:contentId', name: 'SearchDetail', component: SearchDetail},
        // {path: 'editor/:boardId*', name: 'BoardEditor', component: BoardEditor},
      ]
    },
    {
      path: '/manager',
      component: MemberManager,
      beforeEnter: (to, from, next) => {
        console.log("before enter")
        const { loginStore } = useLoginStore()

        let isLogin = sessionStorage.getItem('isLogin')
        if ((isLogin || loginStore.isLogin == true) && loginStore.userClsf == '001') {
          return next();
        } else {
          console.log("before enter: 관리자만 이용할 수 있음")
          next('/login');
        }
      },
    }
  ]
})

export default router
