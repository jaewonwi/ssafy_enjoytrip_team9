import { ref, reactive } from 'vue'
import { defineStore } from 'pinia'

import notLoginUserProfileImageUrl from '/src/assets/noProfile.png'

export const useLoginStore = defineStore('loginStore', () => {

  const loginStore = reactive({
    isLogin: false,

    userName: '',
    userEmail: '',
    userPassword: '',
    userProfileImageUrl: notLoginUserProfileImageUrl, // build했을 때 image를 가져올 수 있도록 세팅
  })

  // login 후에 현재 user 정보를 authStore에 반영
  const setLogin = (payload) => {
    console.log(payload)
    loginStore.isLogin = payload.isLogin;
    loginStore.userName = payload.userName;
    loginStore.userProfileImageUrl = payload.userProfileImageUrl;
    console.log(loginStore)
  }
  
  return { loginStore, setLogin }
})