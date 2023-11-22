<template>
  <div class="hero hero-inner">
    <div class="container">
      <div class="row align-items-center">
        <div class="col-lg-6 mx-auto text-center">
          <div class="intro-wrap">
            <h1 class="mb-0">회원 관리</h1>
          </div>
        </div>
      </div>
    </div>
  </div>
  <member-manager></member-manager>
  <user-list></user-list>

</template>

<script setup>
import MemberManager from '@/components/user/MemberManager.vue';
import UserList from '@/components/user/UserList.vue';

import { useLoginStore } from '@/stores/loginStore'
import { useAdminStore } from '@/stores/adminStore'
import { useRouter } from 'vue-router'

const { loginStore } = useLoginStore()
const { adminStore, userList } = useAdminStore()

const router = useRouter()

</script>