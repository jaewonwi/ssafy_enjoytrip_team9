<template>
  <nav-bar></nav-bar>

  <router-view></router-view>

  <footer-comp></footer-comp>
</template>

<script setup>
import { useLoginStore } from '@/stores/loginStore'
import { RouterView } from 'vue-router'
import NavBar from './components/common/NavBar.vue'
import FooterComp from './components/common/Footer.vue'

const { setLogin } = useLoginStore()

// 로그인 이후에 App.vue
let isLogin = sessionStorage.getItem('isLogin')
if (isLogin == 'true') {
  let userId = sessionStorage.getItem('userId')
  let userNm = sessionStorage.getItem('userNm')
  let userPhone = sessionStorage.getItem('userPhone')
  let userProfileImageUrl = sessionStorage.getItem('userProfileImageUrl')
  let userEmail = sessionStorage.getItem('userEmail')
  let userClsf = sessionStorage.getItem('userClsf')

  setLogin({
    isLogin: true,
    userId: userId,
    userNm: userNm,
    userPhone: userPhone,
    userProfileImageUrl: userProfileImageUrl,
    userEmail: userEmail,
    userClsf: userClsf
  })
}
</script>

<style scoped></style>
