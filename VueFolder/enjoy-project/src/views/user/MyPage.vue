<template>
  <div class="hero hero-inner">
    <div class="container">
      <div class="row align-items-center">
        <div class="col-lg-6 mx-auto text-center">
          <div class="intro-wrap">
            <h1 class="mb-0">마이페이지</h1>
          </div>
        </div>
      </div>
    </div>
  </div>
  <user-info></user-info>
  <user-list v-if="isManager"></user-list>
  <bookmark-list v-else></bookmark-list>
</template>

<script setup>
import UserInfo from '@/components/user/UserInfo.vue'
import BookmarkList from '@/components/user/BookMarkList.vue'
import UserList from '@/components/user/UserList.vue';

const isManager = true
</script>
