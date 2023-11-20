<template>
  <div id="map"></div>  
</template>

<script setup>
import { ref, watch, onMounted } from "vue";

const KAKAO_MAP_KEY = '20fe3797349842110f77c1012478ec1d';

var map;
const positions = ref([]);
const markers = ref([]);

const props = defineProps({ stations: Array, selectStation: Object });
</script>