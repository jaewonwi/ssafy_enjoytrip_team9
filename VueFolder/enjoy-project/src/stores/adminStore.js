import http from '@/common/axios.js'
import { ref, reactive } from 'vue'
import { defineStore } from 'pinia'
import { useRouter } from 'vue-router'

import notLoginUserProfileImageUrl from '/src/assets/profile/noProfile.png'
// import notLoginUserProfileImageUrl from '/upload/defaultProfile/noProfile.png' // build 이후에 사용

export const useAdminStore = defineStore('adminStore', () => {
  const router = useRouter()

  let adminStore = reactive({
    userlist: [],
  })

  // 사용자 목록 (관리자)
  const userList = async () => {
    let { data } = await http.get("users/userList")
    console.log("------------");
    console.log(data.list);
    adminStore.userlist = data.list;
  }

  return { adminStore, userList }
})
