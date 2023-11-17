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
                    :class="{
                      'is-valid': isUserNmFocusAndValid,
                      'is-invalid': isUserNmFocusAndInValid
                    }"
                    @input="validateUserNm"
                    @focus="isUserNmFocus = true"
                    @blur="isUserNmFocus = false"
                  />
                  <div class="valid-feedback">Valid.</div>
                  <div class="invalid-feedback">Invalid.</div>
                </div>
              </div>
              <div class="form-group">
                <input
                  type="email"
                  class="form-control form-control-user"
                  name="userEmail"
                  placeholder="Email Address for ID"
                  v-model="userEmail"
                  :class="{
                    'is-valid': isUserEmailFocusAndValid,
                    'is-invalid': isUserEmailFocusAndInValid
                  }"
                  @input="validateUserEmail"
                  @focus="isUserEmailFocus = true"
                  @blur="isUserEmailFocus = false"
                />
                <div class="valid-feedback">Valid.</div>
                <div id="userEmailInvalidFeedback" class="invalid-feedback">Invalid.</div>
              </div>
              <div class="form-group row">
                <div class="col-sm-6 mb-3 mb-sm-0">
                  <input
                    type="password"
                    class="form-control form-control-user"
                    name="userPwd"
                    placeholder="Password"
                    v-model="userPwd"
                    :class="{
                      'is-valid': isUserPwdFocusAndValid,
                      'is-invalid': isUserPwdFocusAndInValid
                    }"
                    @input="validateUserPwd"
                    @focus="isUserPwdFocus = true"
                    @blur="isUserPwdFocus = false"
                  />
                  <div class="valid-feedback">Valid.</div>
                  <div class="invalid-feedback">
                    1개 이상의 특수문자, 대소문자 및 숫자를 포함하고 8자리 이상이여야 합니다.
                  </div>
                </div>
                <div class="col-sm-6">
                  <input
                    type="password"
                    class="form-control form-control-user"
                    name="userPwd2"
                    placeholder="Repeat Password"
                    v-model="userPwd2"
                    :class="{
                      'is-valid': isUserPwd2FocusAndValid,
                      'is-invalid': isUserPwd2FocusAndInValid
                    }"
                    @input="validateUserPwd2"
                    @focus="isUserPwd2Focus = true"
                    @blur="isUserPwd2Focus = false"
                  />
                  <div class="valid-feedback">Valid.</div>
                  <div class="invalid-feedback">비밀번호가 일치하지 않습니다.</div>
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

// 사용자가 입력한 정보( 회원가입 )
const userNm = ref('')
const userEmail = ref('')
const userPwd = ref('')
const userPwd2 = ref('')

// focus: focus와 blur가 있는 tag에 마우스 클릭을 했을 경우와 안 했을 경우를 판단한다.
const isUserNmFocus = ref(false)
const isUserPwdFocus = ref(false)
const isUserPwd2Focus = ref(false)
const isUserEmailFocus = ref(false)

// validation
const isUserNmValid = ref(false)
const isUserPwdValid = ref(false)
const isUserPwd2Valid = ref(false)
const isUserEmailValid = ref(false)

// focus + valid/invalid <= computed 를 적용
const isUserNmFocusAndValid = computed(() => isUserNmFocus.value && isUserNmValid.value)
const isUserPwdFocusAndValid = computed(() => isUserPwdFocus.value && isUserPwdValid.value)
const isUserPwd2FocusAndValid = computed(() => isUserPwd2Focus.value && isUserPwd2Valid.value)
const isUserEmailFocusAndValid = computed(() => isUserEmailFocus.value && isUserEmailValid.value)

// invalid
const isUserNmFocusAndInValid = computed(() => isUserNmFocus.value && !isUserNmValid.value)
const isUserPwdFocusAndInValid = computed(() => isUserPwdFocus.value && !isUserPwdValid.value)
const isUserPwd2FocusAndInValid = computed(() => isUserPwd2Focus.value && !isUserPwd2Valid.value)
const isUserEmailFocusAndInValid = computed(() => isUserEmailFocus.value && !isUserEmailValid.value)

const validateUserNm = () => {
  isUserNmValid.value = userNm.value.length > 0 ? true : false
}
const validateUserPwd = () => {
  let patternEngAtListOne = new RegExp(/[a-zA-Z]+/) // + for at least one
  let patternSpeAtListOne = new RegExp(/[~!@#$%^&*()_+|<>?:{}]+/) // + for at least one
  let patternNumAtListOne = new RegExp(/[0-9]+/) // + for at least one

  isUserPwdValid.value =
    patternEngAtListOne.test(userPwd.value) &&
    patternSpeAtListOne.test(userPwd.value) &&
    patternNumAtListOne.test(userPwd.value) &&
    userPwd.value.length >= 8
      ? true
      : false
}
const validateUserPwd2 = () => {
  isUserPwd2Valid.value = userPwd.value == userPwd2.value
}
const validateUserEmail = () => {
  let regexp = new RegExp(
    /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i
  )
  // true 또는 false로 리턴
  isUserEmailValid.value = regexp.test(userEmail.value)
}

// 회원가입
const regist = async () => {
  let userObj = {
    userNm: userNm.value,
    userEmail: userEmail.value,
    userPwd: userPwd.value
  }

  try {
    console.log(userObj)
    let { data } = await http.post('/users', userObj)
    console.log(data)
    // 로그인 페이지로 이동
    router.push('/login')
  } catch (error) {
    alert('아이디가 이미 존재합니다.')
    console.log(error)
  }
}
</script>
