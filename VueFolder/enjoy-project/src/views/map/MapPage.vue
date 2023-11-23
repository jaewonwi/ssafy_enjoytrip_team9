<template>
  <div class="hero hero-inner">
    <div class="container">
      <div class="row align-items-center">
        <div class="col-lg-6 mx-auto text-center">
          <div class="intro-wrap">
            <h1 class="mb-0">지도</h1>
          </div>
        </div>
      </div>
    </div>
  </div>

  <div class="untree_co-section">
    <div class="container justify-content-center" style="height: 1000px">
      <select-location></select-location>
      <kakao-map></kakao-map>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'

import KakaoMap from '@/components/map/KakaoMap.vue'
import SelectLocation from '@/components/map/SelectLocation.vue'

// import { useMapStore } from '@/stores/mapStore'
// const { mapStore } = useMapStore()
</script>

<style scoped>
#map {
  width: 100%;
  height: 100%;
}
</style>
