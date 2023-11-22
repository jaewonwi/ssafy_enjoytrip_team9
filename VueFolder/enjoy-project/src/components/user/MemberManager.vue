<template>
  <div class="untree_co-section">
    <div class="container justify-content-center">
      <div class="row">
        <div class="col">
          <div class="p-5">
            <div class="text-center">
              <h1 class="h2 text-gray-900 mb-3 section-title text-center">{{ loginStore.userNm }}</h1>
              <div class="row mb-3 thumbnail-wrapper">
                <div class="container">
                  <img id="imgFileUploadUpdateThumbnail" class="col mb-2" v-bind:src="loginStore.userProfileImageUrl" alt="" style="width: 150px; height: 150px; border-radius: 50%" />
                </div>
              </div>
            </div>
            <div class="form-group">
              <p class="form-control form-control-user">{{ loginStore.userPhone }}</p>
            </div>
            <div class="form-group">
              <p class="form-control form-control-user">{{ loginStore.userEmail }}</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>


<script setup>
import { useLoginStore } from '@/stores/loginStore'
import { useAdminStore } from '@/stores/adminStore'

import { useRouter } from 'vue-router'

const { loginStore } = useLoginStore()
const { adminStore, userList } = useAdminStore()

</script>
