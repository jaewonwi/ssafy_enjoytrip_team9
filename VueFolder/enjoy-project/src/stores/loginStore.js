import http from '@/common/axios.js'
import { ref, reactive } from 'vue'
import { defineStore } from 'pinia'
import { useRouter } from 'vue-router'

import notLoginUserProfileImageUrl from '/src/assets/profile/noProfile.png'
// import notLoginUserProfileImageUrl from '/upload/defaultProfile/noProfile.png' // build 이후에 사용

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
    console.log(options)
    
    try { 
      // put이 아니라 post
      let { data } = await http.post('/users/profiles', formData, options)
      console.log(data)

      if (data.result == 'success') {
        // sessionStorage와 loginStore 갱신
        setUpdate({
          userNm: data.userNm,
          userPhone: data.userPhone,
          userProfileImageUrl: data.userProfileImageUrl,  // data.userProfileImageUrl
        })
        
        alert('수정완료!')
      } else if (data.result == 'inputPwd') {
        alert('비밀번호를 입력하세요')
      } else if (data.result == 'inputPhone') {
        alert('전화번호를 입력하세요')
      } else if (data.result == 'login') {
        setLogout()
        alert('time-out으로 인한 로그아웃!')
      } else {
        alert('수정 형식에 맞춰주세요!!')
      }
    } catch (error) {
      console.log(error)
    }
  }

  // user와 user_profile 삭제
  const deleteUser = async (email) => {
    try {
      let { data } = await http.delete('/users/' + email)
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
    console.log('setLogin: ', payload)
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
    console.log('setLogin: ', loginStore)
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

  const detail = async () => {
    try {
      let { data } = await http.get('/users/' + loginStore.userEmail)
      console.log(data) // userResultDto 확인

      if (data.result == 'login') {
        setLogout();
        alert("time-out으로 인한 로그아웃")
      } else if (data.result == "success") {
        // loginStore 및 sessionStorage에 저장
        let { userDto, userProfileDto } = data

        sessionStorage.setItem('userId', userDto.userId)
        sessionStorage.setItem('userEmail', userDto.userEmail)
        sessionStorage.setItem('userPwd', userDto.userPwd)
        sessionStorage.setItem('userNm', userDto.userNm)
        sessionStorage.setItem('userPhone', userDto.userPhone)
        sessionStorage.setItem('userProfileImageUrl', userDto.userProfile)

        loginStore.userId = userDto.userId;
        loginStore.userEmail = userDto.useruserEmailId;
        loginStore.userPwd = userDto.userPwd;
        loginStore.userNm = userDto.userNm;
        loginStore.userPhone = userDto.userPhone;
        loginStore.userProfileImageUrl = userDto.userProfile;
        console.log(loginStore)
      }
    } catch (error) {
      console.log(error)
    }
  }

  return { loginStore, setLogin, logout, updateUser, deleteUser, detail }
})
