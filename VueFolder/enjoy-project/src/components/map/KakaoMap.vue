<template>
  <div id="map"></div>  
</template>

<script setup>
import { ref, watch, onMounted } from "vue";

const KAKAO_MAP_KEY = '031d62f93a0d302ac9151ec448c8abfc';

var map;
const positions = ref([]);
const markers = ref([]);

const props = defineProps({ stations: Array, selectStation: Object });
</script>