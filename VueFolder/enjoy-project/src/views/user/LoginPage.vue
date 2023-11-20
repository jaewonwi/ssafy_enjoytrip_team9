<template>
  <div class="hero hero-inner">
    <div class="container">
      <div class="row align-items-center">
        <div class="col-lg-6 mx-auto text-center">
          <div class="intro-wrap">
            <h1 class="mb-0">로그인</h1>
          </div>
        </div>
      </div>
    </div>
  </div>

  <div class="untree_co-section">
    <div class="container justify-content-center">
      <div class="row">
        <div class="col">
          <div class="p-5">
            <div class="text-center">
              <h1 class="h2 text-gray-900 mb-4">Login</h1>
            </div>
            <form name="login-form" class="login-form">
              <div class="form-group">
                <input
                  type="email"
                  class="form-control form-control-user"
                  aria-describedby="emailHelp"
                  placeholder="이메일을 입력해주세요"
                  v-model="loginStore.userEmail"
                />
              </div>
              <div class="form-group">
                <input
                  id="inputUserPwd"
                  type="password"
                  class="form-control form-control-user"
                  placeholder="비밀번호를 입력해주세요"
                  v-model="loginStore.userPwd"
                />
              </div>
              <div class="text-center">
                <button class="btn btn-primary" type="button" @click="login">로그인</button>
                <button class="btn btn-primary" type="button" @click="goRegist">회원가입</button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import http from '@/common/axios.js'
import { ref } from 'vue'
import { useLoginStore } from '@/stores/loginStore'
import { useRouter } from 'vue-router'

const { loginStore, setLogin } = useLoginStore()
const router = useRouter()

const login = async () => {
  let loginObj = {
    // v-model은 input tag의 value와 연결하기 때문에 value 속성에 기본값이 있으면 안 된다.
    userEmail: loginStore.userEmail,
    userPwd: loginStore.userPwd
  }

  try {
    let { data } = await http.post('/login', loginObj)
    console.log(data)

    if (data.result == 'success') {

      // authStore에 반영
      setLogin({
        isLogin: true,
        userId: data.userId,
        userNm: data.userNm,
        userPhone: data.userPhone,
        userEmail: data.userEmail,
        userProfileImageUrl: data.userProfileImageUrl,
        userClsf: data.userClsf
      })

      // 메인 페이지로 이동
      router.push('/')
    } else if (data.result == 'fail') {
      alert('이메일 또는 비밀번호를 확인해주세요.')
    }
  } catch (error) {
    console.log(error)
  }
}

// 회원가입 버튼
const goRegist = () => {
  router.push('/regist')
}
</script>
