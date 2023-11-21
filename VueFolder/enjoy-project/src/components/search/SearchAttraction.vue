<template>

  <div class="untree_co-section">
  <div class="container justify-content-center">
    <!-- 지역 필터링 -->
    <div class="row justify-content-center mb-5">
      
      <!-- col-sm-12 col-md-6 mb-3 mb-lg-0 col-lg-4 -->
      <div class="col-10 d-flex justify-content-evenly">
        <div class="col-4">
          <select name id="area1List" class="form-control custom-select" v-model="store.searchStore.sidoCode" @change="store.getGugunList(store.searchStore.sidoCode)">
            <option value="0">시도</option>
            <option v-for="(sido) in store.searchStore.sidoList" :key="sido.sidoCode" :value="sido.sidoCode">
              {{ sido.sidoName }}</option>
          </select>
        </div>
        <div class="col-4">
          <select name id="area2List" class="form-control custom-select" v-model="store.searchStore.gugunCode">
            <option value="0">구군</option>
            <option v-for="(gugun) in store.searchStore.gugunList" :key="gugun.gugunCode" :value="gugun.gugunCode">
              {{ gugun.gugunName }}</option>
          </select>
        </div>
        
        <div class="col-4">
          <select name id="area2List" class="form-control custom-select" v-model="store.searchStore.contentTypeId">
            <option value="0">전체</option>
            <option v-for="(contentType) in store.searchStore.contentTypeList" :key="contentType.code" :value="contentType.code">
              {{ contentType.name }}</option>
          </select>
        </div>

        <button type="button" class="btn btn-primary h-100" @click="getAttractionList">Search</button>
      </div>
    </div>
  
  
      <div class="container">
        <div class="row">
          <div v-for="(attraction) in store.searchStore.list" :key="attraction.contentId" class="col-6 col-md-6 col-lg-3">
            <div class="media-1">
              <a href="#" class="d-block mb-3">
                <img v-if="attraction.firstImage" :src="attraction.firstImage" class="img-fluid">
                <img v-else :src="noImageUrl" class="img-fluid rounded-3 w-100 h-100">
              </a>
              <div class="d-flex">
                <div>
                  <!-- <h3><a href="#">{{ attraction.title }}</a></h3> -->
                  <span class="">{{ attraction.title }}</span>
                  <p>{{ attraction.addr1 }}</p>
                </div>
              </div>
            </div>
          </div>

          
        </div>
      </div>
    </div>
  </div>
  </template>
  
<script setup>
  import { ref, reactive, computed, watch } from 'vue'
  import { useSearchStore } from '@/stores/searchStore';

  import noImageUrl from "/src/assets/noImage.png"

  import http from "@/common/axios.js";

  const store = useSearchStore();
  const altImage = ref(noImageUrl)

  store.getSidoList();
  const getAttractionList = async() => {
    store.attractionList()
    
  }

</script>
