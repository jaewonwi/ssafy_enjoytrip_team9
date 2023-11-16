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
                      'is-valid': isUserNameFocusAndValid,
                      'is-invalid': isUserNameFocusAndInValid
                    }"
                    @input="validateUserName"
                    @focus="isUserNameFocus = true"
                    @blur="isUserNameFocus = false"
                  />
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
                    name="userRepeatPwd"
                    placeholder="Repeat Password"
                    v-model="userRepeatPwd"
                    :class="{
                      'is-valid': isUserPassword2FocusAndValid,
                      'is-invalid': isUserPassword2FocusAndInValid
                    }"
                    @input="validateUserPassword2"
                    @focus="isUserPassword2Focus = true"
                    @blur="isUserPassword2Focus = false"
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

// 사용자가 입력한 정보( 회원가입 )
const userNm = ref('')
const userEmail = ref('')
const userPwd = ref('')
const userRepeatPwd = ref('')

// focus: focus와 blur가 있는 tag에 마우스 클릭을 했을 경우와 안 했을 경우를 판단한다.
const isUserNameFocus = ref(false)
const isUserPasswordFocus = ref(false)
const isUserPassword2Focus = ref(false)
const isUserEmailFocus = ref(false)

// validation
const isUserNameValid = ref(false)
const isUserPasswordValid = ref(false)
const isUserPassword2Valid = ref(false)
const isUserEmailValid = ref(false)

// focus + valid/invalid <= computed 를 적용
const isUserNameFocusAndValid = computed(() => isUserNameFocus.value && isUserNameValid.value)
const isUserPasswordFocusAndValid = computed(
  () => isUserPasswordFocus.value && isUserPasswordValid.value
)
const isUserPassword2FocusAndValid = computed(
  () => isUserPassword2Focus.value && isUserPassword2Valid.value
)
const isUserEmailFocusAndValid = computed(() => isUserEmailFocus.value && isUserEmailValid.value)

// invalid
const isUserNameFocusAndInValid = computed(() => isUserNameFocus.value && !isUserNameValid.value)
const isUserPasswordFocusAndInValid = computed(
  () => isUserPasswordFocus.value && !isUserPasswordValid.value
)
const isUserPassword2FocusAndInValid = computed(
  () => isUserPassword2Focus.value && !isUserPassword2Valid.value
)
const isUserEmailFocusAndInValid = computed(() => isUserEmailFocus.value && !isUserEmailValid.value)

const validateUserName = () => {
  isUserNameValid.value = userName.value.length > 0 ? true : false
}
const validateUserPassword = () => {
  let patternEngAtListOne = new RegExp(/[a-zA-Z]+/) // + for at least one
  let patternSpeAtListOne = new RegExp(/[~!@#$%^&*()_+|<>?:{}]+/) // + for at least one
  let patternNumAtListOne = new RegExp(/[0-9]+/) // + for at least one

  isUserPasswordValid.value =
    patternEngAtListOne.test(userPassword.value) &&
    patternSpeAtListOne.test(userPassword.value) &&
    patternNumAtListOne.test(userPassword.value) &&
    userPassword.value.length >= 8
      ? true
      : false
}
const validateUserPassword2 = () => {
  isUserPassword2Valid.value = userPassword.value == userPassword2.value
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
