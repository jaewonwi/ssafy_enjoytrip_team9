import http from '@/common/axios.js'
import { ref, reactive } from 'vue'
import { defineStore } from 'pinia'
import { useRouter } from 'vue-router'

import notLoginUserProfileImageUrl from '/src/assets/profile/noProfile.png'

export const useLoginStore = defineStore('loginStore', () => {
  const router = useRouter()

  const loginStore = reactive({
    isLogin: false,
    userId: '',
    userNm: '',
    userPhone: '',
    userEmail: 'jaewon@n.com',
    userPwd: 'qwer1234!',
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

  const updateUser = async (formData, options) => {

    let updateUserObj = {
      userEmail: loginStore.userEmail,
      userNm: formData.userNm,
      userPwd: formData.userPwd,
      userPhone: formData.userPhone,
      userProfileImageUrl: formData.userProfileImageUrl
    }

    // 이름, 전화번호, 비밀번호, 프로필 경로를 받는다.
    // 프로필을 제외한 업데이트
    if (formData.userProfileImageUrl == loginStore.userProfileImageUrl) {
      try { 
        // put이 아니라 post
        let { data } = await http.post('/users/profiles', updateUserObj, options)
        console.log(data)


  
        if (data.result == 'success') {
          // sessionStorage와 loginStore 갱신
          setUpdate({
            userNm: data.userNm,
            userPhone: data.userPhone,
            userEmail: data.userEmail,
            userProfileImageUrl: data.userProfileImageUrl,  // data.userProfileImageUrl
            userClsf: data.userClsf
          })
        } if (data.result == 'login') {
          setLogout()
          alert('time-out으로 인한 로그아웃!')
        } else {
          alert('수정 형식에 맞춰주세요!!')
        }
      } catch (error) {
        console.log(error)
      }
    } else {  // 프로필을 포함한 업데이트 -> post
      try {

      } catch (error) {
        console.log(error)
      }

    }
    
    // try { 
    //   // put이 아니라 post
    //   let { data } = await http.put('/users', updateUserObj, formData, options)
    //   console.log(data)

    //   if (data.result == 'success') {
    //     setUpdate({
    //       userNm: data.userNm,
    //       userPhone: data.userPhone,
    //       userEmail: data.userEmail,
    //       userProfileImageUrl: data.userProfileImageUrl,  // data.userProfileImageUrl
    //       userClsf: data.userClsf
    //     })
    //   } if (data.result == 'login') {
    //     setLogout()
    //     alert('time-out으로 인한 로그아웃!')
    //   } else {
    //     alert('수정 형식에 맞춰주세요!!')
    //   }
    // } catch (error) {
    //   console.log(error)
    // }
  }

  const deleteUser = async (payload) => {
    try {
      let { data } = await http.delete('/users/' + payload)
      console.log(data)
      if (data.result == 'success') {
        // session 삭제는 백에서 진행
        setLogout()
        alert('탈퇴!!')
      } else if (data.result == 'login') {
        logout()
        alert('time-out으로 로그아웃 되었습니다. 다시 로그인해주세요')
      }
    } catch (error) {
      console.log(error)
    }
  }

  // login 후에 현재 user 정보를 sessionStorage와 authStore에 반영
  const setLogin = (payload) => {
    console.log('setLogin: ' + payload)
    sessionStorage.setItem('isLogin', 'true')
    sessionStorage.setItem('userId', payload.userId)
    sessionStorage.setItem('userNm', payload.userNm)
    sessionStorage.setItem('userPhone', payload.userPhone)
    sessionStorage.setItem('userProfileImageUrl', payload.userProfileImageUrl)
    sessionStorage.setItem('userEmail', payload.userEmail)
    sessionStorage.setItem('userClsf', payload.userClsf)

    loginStore.isLogin = payload.isLogin
    loginStore.userId = payload.userId
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
    sessionStorage.removeItem('userId', '')
    sessionStorage.removeItem('userNm', '')
    sessionStorage.removeItem('userPhone', '')
    sessionStorage.removeItem('userProfileImageUrl')
    sessionStorage.removeItem('userEmail')
    sessionStorage.removeItem('userClsf')

    // loginStore
    loginStore.isLogin = false
    loginStore.userId = ''
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
    sessionStorage.setItem('userProfileImageUrl', payload.userProfileImageUrl)

    loginStore.userNm = payload.userNm
    loginStore.userPhone = payload.userPhone
    loginStore.userProfileImageUrl = payload.userProfileImageUrl
    console.log('setUpdate: ' + loginStore)
  }

  return { loginStore, setLogin, logout, updateUser }
})
