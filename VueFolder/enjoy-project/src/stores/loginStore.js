import { ref, reactive } from 'vue'
import { defineStore } from 'pinia'

import notLoginUserProfileImageUrl from '/src/assets/noProfile.png'

export const useLoginStore = defineStore('loginStore', () => {

  // 로그인 여부 
  const loginStore = reactive({
    isLogin: false,

    userNm: '',
    userEmail: 'kdh@n.com',
    userProfileImageUrl: notLoginUserProfileImageUrl, // build했을 때 image를 가져올 수 있도록 세팅
    userClsf: '',
  })

  // login 후에 현재 user 정보를 authStore에 반영
  const setLogin = (payload) => {
    console.log("loginStore: " + payload)
    loginStore.isLogin = payload.isLogin;
    loginStore.userNm = payload.userNm;
    loginStore.userEmail = payload.userEmail;
    loginStore.userProfileImageUrl = payload.userProfileImageUrl;
    loginStore.userClsf = payload.userClsf;
    console.log("loginStore: " + loginStore)
  }
  
  return { loginStore, setLogin }
})