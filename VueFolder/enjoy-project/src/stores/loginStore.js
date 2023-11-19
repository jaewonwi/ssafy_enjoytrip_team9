import http from '@/common/axios.js'
import { ref, reactive } from 'vue'
import { defineStore } from 'pinia'
import { useRouter } from 'vue-router'

import notLoginUserProfileImageUrl from '/src/assets/profile/noProfile.png'

export const useLoginStore = defineStore('loginStore', () => {
  const router = useRouter()

  const loginStore = reactive({
    isLogin: false,

    userNm: '',
    userPhone: '',
    userEmail: 'kdh@n.com',
    userPwd: '1234',
    userProfileImageUrl: notLoginUserProfileImageUrl, // build했을 때 image를 가져올 수 있도록 세팅

    // 일반 사용자와 관리자
    userClsf: ''
  })

  const logout = async () => {
    try {
      let { data } = await http.get('/logout')
      if (data.result == 'success') {
        setLogout()
      } else if (data.result == 'login') {  // interceptor 이용
        console.log('time-out으로 인한 로그아웃 처리가 불가능할 때 대응법')
        setLogout()
      }
    } catch (error) {
      console.log(error)
    }
  }

  const updateUser = async (payload) => {
    let updateUserObj = {
      userEmail: loginStore.userEmail,
      userNm: payload.userNm,
      userPwd: payload.userPwd,
      userPhone: payload.userPhone,
      userProfileImageUrl: loginStore.userProfileImageUrl
    }
    // 이름, 전화번호, 비밀번호를 받는다.
    try {
      let { data } = await http.put('/users', updateUserObj)
      console.log(data)

      if (data.result == 'success') {
        setUpdate({
          userNm: data.userNm,
          userPhone: data.userPhone,
          userEmail: data.userEmail,
          userProfileImageUrl: data.userProfileImageUrl,
          userClsf: data.userClsf
        })
      }
    } catch (error) {
      console.log(error)
    }
  }

  // login 후에 현재 user 정보를 sessionStorage와 authStore에 반영
  const setLogin = (payload) => {
    console.log('setLogin: ' + payload)
    sessionStorage.setItem('isLogin', 'true')
    sessionStorage.setItem('userNm', payload.userNm)
    sessionStorage.setItem('userPhone', payload.userPhone)
    sessionStorage.setItem('userProfileImageUrl', payload.userProfileImageUrl)
    sessionStorage.setItem('userEmail', payload.userEmail)
    sessionStorage.setItem('userClsf', payload.userClsf)

    loginStore.isLogin = payload.isLogin
    loginStore.userNm = payload.userNm
    loginStore.userPhone = payload.userPhone
    loginStore.userEmail = payload.userEmail
    loginStore.userProfileImageUrl = payload.userProfileImageUrl
    loginStore.userClsf = payload.userClsf
    console.log('setLogin: ' + loginStore)
  }

  const setLogout = () => {
    // sessionStorage
    sessionStorage.removeItem('isLogin')
    sessionStorage.removeItem('userNm', '')
    sessionStorage.removeItem('userPhone', '')
    sessionStorage.removeItem('userProfileImageUrl')
    sessionStorage.removeItem('userEmail')
    sessionStorage.removeItem('userClsf')

    // loginStore
    loginStore.isLogin = false
    loginStore.userNm = ''
    loginStore.userPhone = ''
    loginStore.userEmail = ''
    loginStore.userPwd = ''
    loginStore.userProfileImageUrl = notLoginUserProfileImageUrl
    loginStore.userClsf = ''

    // 로그인 페이지로 이동
    router.push('/login')
  }

  const setUpdate = (payload) => {
    console.log('setUpdate: ' + payload)
    sessionStorage.setItem('userNm', payload.userNm)
    sessionStorage.setItem('userPhone', payload.userPhone)
    // 수정하면 userDto의 profileUrl에 null이 들어간다.
    // sessionStorage.setItem('userProfileImageUrl', payload.userProfileImageUrl)

    loginStore.userNm = payload.userNm
    loginStore.userPhone = payload.userPhone
    console.log('setUpdate: ' + loginStore)
  }

  return { loginStore, setLogin, logout, updateUser }
})
