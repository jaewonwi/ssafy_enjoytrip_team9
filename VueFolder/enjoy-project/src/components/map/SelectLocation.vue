<template>
  <div class="container">
    <div class="row">
      <select class="select-loc form-select form-select-sm w-25 pl-2 m-3 h-100" @change="getGugun" v-model="mapStore.selectSido">
        <option v-for="(sido, index) in mapStore.sidoList" :key="index" :value="sido.sidoCode">{{ sido.sidoName }}</option>
      </select>
      <select class="select-loc form-select form-select-sm w-25 pl-2 m-3 h-100" @change="getPin" v-model="mapStore.selectGugun">
        <option v-for="(gugun, index) in mapStore.gugunList" :key="index" :value="gugun.gugunCode">{{ gugun.gugunName }}</option>
      </select>
    </div>
  </div>
</template>

<script setup>
import { useMapStore } from '@/stores/mapStore'
const { mapStore, getSidoList, getGugunList, getPinList } = useMapStore()

// 시도 세팅 및 선택 후에 구군 불러오기
getSidoList()
const getGugun = () => {
  getGugunList(mapStore.selectSido)
}

// 구군 선택 후 pin이 찍힐 위치 mapStore에 저장하기
// KakaoMap.vue에서 mapStore에 접근하여 pin 위치를 가져가서 맵에 찍는다.
const getPin = () => {
  getPinList(mapStore.selectSido, mapStore.selectGugun)
}
</script>
.select-loc { height: 50px; }
<style scoped></style>
