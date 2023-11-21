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
              <option v-for="sido in store.searchStore.sidoList" :key="sido.sidoCode" :value="sido.sidoCode">
                {{ sido.sidoName }}
              </option>
            </select>
          </div>
          <div class="col-4">
            <select name id="area2List" class="form-control custom-select" v-model="store.searchStore.gugunCode">
              <option value="0">구군</option>
              <option v-for="gugun in store.searchStore.gugunList" :key="gugun.gugunCode" :value="gugun.gugunCode">
                {{ gugun.gugunName }}
              </option>
            </select>
          </div>

          <div class="col-4">
            <select name id="area2List" class="form-control custom-select" v-model="store.searchStore.contentTypeId">
              <option value="0">전체</option>
              <option v-for="contentType in store.searchStore.contentTypeList" :key="contentType.code" :value="contentType.code">
                {{ contentType.name }}
              </option>
            </select>
          </div>

          <button type="button" class="btn btn-primary h-100" @click="getAttractionList">Search</button>
        </div>
      </div>

      <div class="container">
        <div class="row">
          <div v-for="attraction in store.searchStore.list" :key="attraction.contentId" class="col-6 col-md-6 col-lg-3">
            <div class="media-1 position-relative">
              <!-- <img id="bookmark" :src="bookMarkUrl" class="position-absolute m-1 rounded-0" style="max-width: 50px; max-height: 40px" /> -->
              <!--  -->
                <img
                  class="position-absolute m-1 rounded-0"
                  type="checkbox"
                  style="max-width: 50px; max-height: 40px"
                  :id="attraction.contentId"
                  :src="bookMarkOFFUrl"
                  @click="changeImageUrlHandler(attraction.contentId)"
                />
              <a href="#" class="d-block mb-3">
                <img v-if="attraction.firstImage" :src="attraction.firstImage" class="img-fluid" />
                <img v-else :src="noImageUrl" class="img-fluid rounded-3 w-100 h-100" />
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
import { ref, reactive, computed, watch, onUpdated } from 'vue'
import { useSearchStore } from '@/stores/searchStore'
import { useBookmarkStore } from '@/stores/bookmarkStore'
import { useLoginStore } from '@/stores/loginStore'

import bookMarkOFFUrl from '/src/assets/bookmark/bookmarkOFF.png'
import bookMarkONUrl from '/src/assets/bookmark/bookmarkON.png'
import noImageUrl from '/src/assets/noImage.png'

import http from '@/common/axios.js'

const store = useSearchStore()
const altImage = ref(noImageUrl)
const {bookmarkStore, insertBookmark, deleteBookmark} = useBookmarkStore()
const {loginStore} = useLoginStore()

store.getSidoList()
const getAttractionList = async () => {
  store.attractionList()

  // 유저가 선택한 북마크가 있으면 보여주기

}

// toggle event handler
const changeImageUrlHandler = (contentId) => {
  // event.target
  const curImgTag = document.getElementById(contentId)
  const imagePath = curImgTag.src.replace(window.location.origin, '') // http://localhost:5173/ 까지 제거
  if (imagePath == bookMarkOFFUrl) {
    // 북마크 등록( userId, contentId(관광지) 필요 )
    console.log("userId: ", loginStore.userId)
    insertBookmark(loginStore.userId, contentId)

    curImgTag.src = bookMarkONUrl
  } else {
    // 북마크 삭제( userId, contentId(관광지) 필요 )
    deleteBookmark(loginStore.userId, contentId)

    curImgTag.src = bookMarkOFFUrl
  }
}
</script>
