<template>
  <!-- navbar -->
  <div class="site-mobile-menu site-navbar-target">
    <div class="site-mobile-menu-header">
      <div class="site-mobile-menu-close">
        <span class="icofont-close js-menu-toggle"></span>
      </div>
    </div>
    <div class="site-mobile-menu-body"></div>
  </div>

  <nav class="site-nav">
    <div class="container">
      <div class="site-navigation">
        <a href="/" class="logo m-0 under-line-none">Tour</a>
        <ul class="js-clone-nav d-none d-lg-inline-block text-left site-menu float-right">
          <li class="active">
            <router-link to="/" class="nav-link">Home</router-link>
          </li>
          <li><router-link to="/board">게시판</router-link></li>
          <li><a href="#">Search</a></li>
          <li><a href="map/map.html">Map</a></li>

          <!-- User 기능  -->
          <li class="nav-item" v-show="!loginStore.isLogin">
            <router-link to="/login" class="nav-link">로그인</router-link>
          </li>
          <li class="nav-item" v-show="loginStore.isLogin">
            <a href="#">마이페이지</a>
          </li>
          <li class="nav-item" v-show="loginStore.isLogin">
            <a href="#" @click="logout">로그아웃</a>
          </li>
        </ul>

        <a
          href="#"
          class="burger ml-auto mr-5 float-right site-menu-toggle js-menu-toggle d-inline-block d-lg-none light"
          data-toggle="collapse"
          data-target="#main-navbar"
        >
          <span></span>
        </a>
      </div>
    </div>
  </nav>
</template>

<script setup>
import http from '@/common/axios.js'
import notLoginUserProfileImageUrl from '/src/assets/noProfile.png'
import { useLoginStore } from '@/stores/loginStore'
import { useRouter } from 'vue-router'

const router = useRouter()
const { loginStore, setLogin } = useLoginStore()

const logout = async () => {
  try {
    let { data } = await http.get('/logout')
    console.log(data)

    if (data.result == 'success') {
      // session storage 삭제
      sessionStorage.removeItem('isLogin')
      sessionStorage.removeItem('userEmail')
      sessionStorage.removeItem('userName')
      sessionStorage.removeItem('userProfileImageUrl')

      // loginStore 변경: 로그아웃 이후에 페이지 이동을 막는다.
      setLogin({
        isLogin: false,
        userNm: '',
        userEmail: '',
        userProfileImageUrl: notLoginUserProfileImageUrl
      })

      // 메인 페이지로 이동
      router.push('/')
    }
  } catch (error) {
    console.log(error)
  }
}
</script>

<style scoped>
.under-line-none {
  text-decoration: none;
}
</style>
