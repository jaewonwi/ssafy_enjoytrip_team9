<script setup>
import { reactive, computed, watch, ref } from 'vue'
//import { useAttractionStore } from '@/stores/attractionStore';
import http from '@/common/axios.js'
//const store= useAttractionStore();

//   const init = async ()=>{

//     await getSidoList();
//     console.log("...............................");
//     console.log(sidoList);
//   }
// init();

store.getSidoList()

//console.log(sidoList)
</script>

<template>
  <div class="untree_co-section">
    <div class="container justify-content-center">
      <!-- 지역 필터링 -->
      <div class="row justify-content-center mb-5">
        <!-- col-sm-12 col-md-6 mb-3 mb-lg-0 col-lg-4 -->
        <div class="col-9 d-flex justify-content-evenly">
          <div class="col-6">
            <select name id="area1List" class="form-control custom-select">
              <option value="">시도</option>
              <option v-for="sido in store.sidoList" :key="sido.sidoCode" :value="sido.sidoCode">{{ sido.sidoName }}</option>
            </select>
          </div>
          <div class="col-6">
            <select name id="area2List" class="form-control custom-select">
              <option value="">구군</option>
            </select>
          </div>
          <button id="btnSearch" type="button" class="btn btn-primary h-100">Search</button>
        </div>

        <!-- </div> -->
      </div>

      <div class="container">
        <div class="row">
          <div class="col-6 col-md-6 col-lg-3">
            <div class="media-1">
              <a href="#" class="d-block mb-3"><img src="images/hero-slider-1.jpg" alt="Image" class="img-fluid" /></a>
              <div class="d-flex">
                <div>
                  <h3><a href="#">Excellence in Travel</a></h3>
                  <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts.</p>
                </div>
              </div>
            </div>
          </div>
          <div class="col-6 col-md-6 col-lg-3">
            <div class="media-1">
              <a href="#" class="d-block mb-3"><img src="images/hero-slider-2.jpg" alt="Image" class="img-fluid" /></a>
              <div class="d-flex">
                <div>
                  <h3><a href="#">Discovering Best</a></h3>
                  <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts.</p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
