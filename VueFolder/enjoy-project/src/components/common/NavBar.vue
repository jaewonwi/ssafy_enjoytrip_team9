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

  <nav class="navbar navbar-expand-lg navbar-light navbar-color">
    <div class="container">
      <a href="/" class="logo m-0 under-line-none">Tour</a>
      <button
        class="navbar-toggler"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#navbarSupportedContent"
        aria-controls="navbarSupportedContent"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="burger ml-auto float-right d-inline-block d-lg-none light"></span>
      </button>

      <div class="collapse navbar-collapse justify-content-end" id="navbarSupportedContent">
        <ul class="navbar-nav mb-2 mb-lg-0 float-right">
          <li class="active nav-item">
            <router-link to="/" class="nav-link text-white">Home</router-link>
          </li>
          <li class="nav-item">
            <router-link to="/board" class="nav-link text-white">게시판</router-link>
          </li>
          <li class="nav-item"><a class="nav-link text-white" href="#">Search</a></li>
          <li class="nav-item"><a class="nav-link text-white" href="#">Map</a></li>

          <!-- User 기능  -->
          <li class="nav-item" v-show="!loginStore.isLogin">
            <router-link to="/login" class="nav-link text-white">로그인</router-link>
          </li>
          <li class="nav-item" v-show="loginStore.isLogin">
            <a class="nav-link text-white" href="#">마이페이지</a>
          </li>
          <li class="nav-item" v-show="loginStore.isLogin">
            <a class="nav-link text-white" href="#" @click="logout">로그아웃</a>
          </li>
        </ul>
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

.navbar-color {
  background-color: rgb(26, 55, 77);
}
</style>
