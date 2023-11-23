<template>
    <hr />
    <div class="untree_co-section">
        <div class="container">
            <div class="row text-center justify-content-center mb-5">
                <div class="col-lg-7"><h2 class="section-title text-center">사용자 관리</h2></div>
            </div>
       </div>

       <div class="container">
        <div class="row text-center mb-5" >
        <div v-for="user in adminStore.userlist" :key="user.userId" class="col-6 col-md-6 col-lg-3 mb-3">
          <div class="media-1 position-relative">
            <button
              class="position-absolute btn btn-primary m-2 rounded-1"
              style="pointer-events: auto"
              :id="user.userId"
              @click.stop="userDelete(user.userEmail)"
            >탈퇴</button>
            <a href="#" class="d-block mb-3 z-2">
              <img v-if="user.userProfile" :src="user.userProfile"  class="img-fluid rounded-3 w-100 h-100" />
              <img v-else :src="noImageUrl" class="img-fluid rounded-3 w-100 h-100" />
            </a>
            <div class="d-flex">
              <div>
                <span class="">{{ user.userNm }}</span>
                <p>{{ user.userEmail }}</p>
              </div>
            </div>
          </div>
        </div>
        </div>
      </div>
    </div>
</template>


<script setup>
  import http from '@/common/axios.js'
  import { useAdminStore } from '@/stores/adminStore';

  import noImageUrl from '@/assets/noProfile.png'

  const { adminStore, userList } = useAdminStore()

  const userDelete = async (userEmail) => {
    let result = confirm('해당 사용자를 삭제하시겠습니까?')
    if (result) {
      try {
        let { data } = await http.delete("/users/admin/"+userEmail);  
        console.log(userEmail + "님을 삭제합니다.")
        userList()
      } catch (error) {
        console.log(error)
      }
    }
  }

  userList();
</script>