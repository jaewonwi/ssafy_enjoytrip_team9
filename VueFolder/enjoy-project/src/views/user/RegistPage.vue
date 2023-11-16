<template>
  <!-- 회원 가입 form -->
  <div class="hero hero-inner">
    <div class="container">
      <div class="row align-items-center">
        <div class="col-lg-6 mx-auto text-center">
          <div class="intro-wrap">
            <h1 class="mb-0">회원가입</h1>
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
            <form class="user">
              <div class="form-group">
                <div class="mb-sm-0">
                  <input
                    type="text"
                    class="form-control form-control-user"
                    name="userNm"
                    placeholder="Name"
                    v-model="userNm"
                  />
                </div>
              </div>
              <div class="form-group">
                <input
                  type="email"
                  class="form-control form-control-user"
                  v-model="userEmail"
                  name="userEmail"
                  placeholder="Email Address for ID"
                />
              </div>
              <div class="form-group row">
                <div class="col-sm-6 mb-3 mb-sm-0">
                  <input
                    type="password"
                    class="form-control form-control-user"
                    v-model="userPwd"
                    name="userPwd"
                    placeholder="Password"
                  />
                </div>
                <div class="col-sm-6">
                  <input
                    type="password"
                    class="form-control form-control-user"
                    v-model="userRepeatPwd"
                    name="userRepeatPwd"
                    placeholder="Repeat Password"
                  />
                </div>
              </div>
              <div class="text-center">
                <button class="btn btn-primary" id="btnRegist" type="button" @click="regist">
                  회원가입
                </button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue'
import { useRouter } from 'vue-router'
import http from '@/common/axios.js'
const router = useRouter()

// 사용자가 입력한 정보
const userNm = ref('')
const userEmail = ref('')
const userPwd = ref('')
const userRepeatPwd = ref('')

const regist = async () => {
  let userObj = {
    userNm: userNm.value,
    userEmail: userEmail.value,
    userPwd: userPwd.value,
    userRepeatPwd: userRepeatPwd.value
  }

  try {
    console.log(userObj)
    let { data } = await http.post('/users', userObj)
    console.log(data)
    if (data == 1) {
      // 로그인 페이지로 이동
      router.push('/login')
    } else {
      // 아이디가 이미 있는 경우 - 다시 입력
      alert('아이디가 이미 존재합니다.')
    }
  } catch (error) {
    console.log(error)
  }
}
</script>
