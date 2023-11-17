<template>
  <div class="untree_co-section">
    <div class="container justify-content-center">
      <div class="row">
        <div class="col">
          <div class="p-5">
            <div class="text-center">
              <h1 class="h2 text-gray-900 mb-4 section-title text-center">내 정보</h1>
            </div>
            <div class="form-group">
              <input
                type="text"
                class="form-control form-control-user"
                id="inputName"
                v-model="loginStore.userNm"
              />
            </div>
            <div class="form-group">
              <p class="form-control form-control-user">{{ loginStore.userEmail }}</p>
            </div>
            <div class="form-group">
              <div class="row">
                <div class="col-sm">
                  <input
                    type="password"
                    class="form-control form-control-user"
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
                <div class="col-sm">
                  <input
                    type="password"
                    class="form-control form-control-user"
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
            </div>
            <div class="text-center">
              <button class="btn btn-primary" type="button">수정하기</button>
              <button class="btn btn-secondary" type="button">탈퇴하기</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue'
import { useLoginStore } from '@/stores/loginStore'
import http from '@/common/axios.js'

const { loginStore } = useLoginStore()

const userNm = loginStore.userNm
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
</script>
