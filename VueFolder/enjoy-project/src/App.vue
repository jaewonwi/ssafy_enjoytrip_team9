<script setup>
import { RouterView } from 'vue-router'
import NavBar from './components/common/NavBar.vue'
import FooterComp from './components/common/Footer.vue'
</script>

<template>
  <nav-bar></nav-bar>

  <router-view></router-view>

  <footer-comp></footer-comp>
</template>

<style scoped></style>
