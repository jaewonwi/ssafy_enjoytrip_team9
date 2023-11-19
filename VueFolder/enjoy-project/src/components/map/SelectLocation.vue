<template>
  <div class="container">
    <div class="row">
      <select class="select-loc form-select form-select-sm w-25 pl-2 m-3 h-100" @change="getGugun" v-model="selectLoc.sidoCode">
        <option v-for="(sido, index) in sidoList" :key="index" :value="sido.sidoCode">{{ sido.sidoName }}</option>
      </select>
      <select class="select-loc form-select form-select-sm w-25 pl-2 m-3 h-100">
        <option v-for="(gugun, index) in gugunList" :key="index" :value="gugun.gugunName"></option>
      </select>
    </div>
  </div>
</template>

<script setup>
import { useMapStore } from '@/stores/mapStore'
const { sidoList, getSidoList, gugunList, getGugunList, selectLoc } = useMapStore()


getSidoList()

const getGugun = () => {
  getGugunList(selectLoc.sidoCode)
}
</script>
.select-loc { height: 50px; }
<style scoped></style>
