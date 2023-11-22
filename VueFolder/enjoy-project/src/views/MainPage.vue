<template>
  <div class="hero" style="background-color: #1A374D;">
    <div class="container">
      <div class="row align-items-center">
        <div class="col-lg-7">
          <div class="intro-wrap">
            <h1 class="mb-4"><span class="d-block">여행을 계획해볼까요?</span> <span class="typed-words"></span>는 어떠세요?</h1>

            <div class="row">
              <div class="col-12">
                <form class="form">
                  <div class="row">
                    <div class="col-sm-12 col-md-6 mb-3 mb-lg-0 col-lg-5">
                      <select name id="area1List" class="form-control custom-select" v-model="store.searchStore.sidoCode" @change="store.getGugunList(store.searchStore.sidoCode)">
                        <option value="0">시도</option>
                        <option v-for="(sido) in store.searchStore.sidoList" :key="sido.sidoCode" :value="sido.sidoCode">
                          {{ sido.sidoName }}</option>
                      </select>
                    </div>
                    <div class="col-sm-12 col-md-6 mb-3 mb-lg-0 col-lg-5">
                      <select name id="area2List" class="form-control custom-select" v-model="store.searchStore.gugunCode">
                        <option value="0">구군</option>
                        <option v-for="(gugun) in store.searchStore.gugunList" :key="gugun.gugunCode" :value="gugun.gugunCode">
                          {{ gugun.gugunName }}</option>
                      </select>
                    </div>
                    <div class="col-sm-12 col-md-6 mb-3 mb-lg-0 col-lg-2">
                      <!-- <button type="button" class="btn btn-primary h-100" @click="getAttractionList">Search</button> -->
                      <router-link to="/search" type="button" class="btn btn-primary h-100">Search</router-link>
                    </div>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
        <div class="col-lg-5">
          <div class="slides">
            <img src="/images/hero-slider-1.jpg" alt="Image" class="img-fluid active" />
            <img src="/images/hero-slider-2.jpg" alt="Image" class="img-fluid" />
            <img src="/images/hero-slider-3.jpg" alt="Image" class="img-fluid" />
            <img src="/images/hero-slider-4.jpg" alt="Image" class="img-fluid" />
            <img src="/images/hero-slider-5.jpg" alt="Image" class="img-fluid" />
            <img src="/images/hero-slider-5.jpg" alt="Image" class="img-fluid" />
          </div>
        </div>
      </div>
    </div>
  </div>

  <random-list></random-list>

</template>

<script setup>
import { ref, reactive, computed, watch } from 'vue'
import { useSearchStore } from '@/stores/searchStore'
import http from "@/common/axios.js";

// component
import RandomList from '@/components/main/RandomList.vue'

const store = useSearchStore();

store.getSidoList();
const getAttractionList = async() => {
  store.attractionList()  
}


$(function () {
  var slides = $('.slides'),
    images = slides.find('img')

  images.each(function (i) {
    $(this).attr('data-id', i + 1)
  })

  var typed = new Typed('.typed-words', {
    strings: [' 제주', ' 대구', ' 경주', ' 강원도', ' 대전', ' 부산'],
    typeSpeed: 80,
    backSpeed: 80,
    backDelay: 4000,
    startDelay: 1000,
    loop: true,
    showCursor: true,
    preStringTyped: (arrayPos, self) => {
      arrayPos++
      //console.log(arrayPos);
      $('.slides img').removeClass('active')
      $('.slides img[data-id="' + arrayPos + '"]').addClass('active')
    }
  })
})



</script>
