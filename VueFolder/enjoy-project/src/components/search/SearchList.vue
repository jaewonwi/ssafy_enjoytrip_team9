<template>
  <div class="untree_co-section">
    <div class="container justify-content-center">
      <!-- 지역 필터링 -->
      <div class="row justify-content-center mb-5">
        <!-- col-sm-12 col-md-6 mb-3 mb-lg-0 col-lg-4 -->
        <div class="col-10 d-flex justify-content-evenly">
          <div class="col-4">
            <select name id="area1List" class="form-control custom-select" v-model="searchStore.sidoCode" @change="getGugunList(searchStore.sidoCode)">
              <option value="0">시도</option>
              <option v-for="sido in searchStore.sidoList" :key="sido.sidoCode" :value="sido.sidoCode">
                {{ sido.sidoName }}
              </option>
            </select>
          </div>
          <div class="col-4">
            <select name id="area2List" class="form-control custom-select" v-model="searchStore.gugunCode" @change="getTypeList">
              <option value="0">구군</option>
              <option v-for="gugun in searchStore.gugunList" :key="gugun.gugunCode" :value="gugun.gugunCode">
                {{ gugun.gugunName }}
              </option>
            </select>
          </div>

          <div class="col-4">
            <select name id="area2List" class="form-control custom-select" v-model="searchStore.contentTypeId">
              <option value="0">전체</option>
              <option v-for="contentType in searchStore.contentTypeList" :key="contentType.code" :value="contentType.code">
                {{ contentType.name }}
              </option>
            </select>
          </div>
          <button type="button" class="btn btn-primary h-100" @click="attractionList()">Search</button>
        </div>
      </div>

      <div class="container">
        <div class="row">
          <div v-for="attraction in searchStore.list" :key="attraction.contentId" class="p-1 col-6 col-md-6 col-lg-3" @click="attractionDetail(attraction.contentId)">
            <div class="media-1 position-relative">
              <span v-if="loginStore.isLogin">
                <img
                  class="position-absolute m-1 rounded-0"
                  type="checkbox"
                  style="max-width: 50px; max-height: 40px; pointer-events: auto"
                  :id="attraction.contentId"
                  :src="bookMarkONUrl"
                  v-if="attraction.isBookmark"
                  @click.stop="changeImageUrlHandler(attraction.contentId)"
                />
                <img
                  class="position-absolute m-1 rounded-0"
                  type="checkbox"
                  style="max-width: 50px; max-height: 40px; pointer-events: auto"
                  :id="attraction.contentId"
                  :src="bookMarkOFFUrl"
                  v-if="!attraction.isBookmark"
                  @click.stop="changeImageUrlHandler(attraction.contentId)"
                />
              </span>

              <a href="#" class="d-block mb-3">
                <img v-if="attraction.firstImage" :src="attraction.firstImage" class="img-fluid" />
                <img v-else :src="noImageUrl" class="img-fluid rounded-3 w-100 h-100" />
              </a>
              <div class="d-flex">
                <div>
                  <span class="">{{ attraction.title }}</span>
                  <p>{{ attraction.addr1 }}</p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <PaginationUI v-on:call-parent="movePage"></PaginationUI>
  </div>
</template>

<script setup>
// basic
import { ref } from 'vue'
import http from '@/common/axios.js'

// store
import { useSearchStore } from '@/stores/searchStore'
import { useBookmarkStore } from '@/stores/bookmarkStore'
import { useLoginStore } from '@/stores/loginStore'

// router
import { useRouter } from 'vue-router'

// component
import PaginationUI from '@/components/search/PaginationUI.vue'

import bookMarkOFFUrl from '/src/assets/bookmark/bookmarkOFF.png'
import bookMarkONUrl from '/src/assets/bookmark/bookmarkON.png'
import noImageUrl from '/src/assets/noImage.png'

const { searchStore, getSidoList, getGugunList, getTypeList, attractionList, setSearchMovePage } = useSearchStore()
const { bookmarkStore, insertBookmark, deleteBookmark, getBookmarkListOfCurUser } = useBookmarkStore()
const { loginStore } = useLoginStore()

const router = useRouter()

// 페이지 로딩 시 init
const initList = async () => {
  getSidoList()
  attractionList()
}

initList()

const attractionDetail = async (contentId) => {
  try {
    router.push({
      name: 'SearchDetail',
      params: { contentId }
    })
  } catch (error) {
    console.log('attractionDetailVue: error: ')
    console.log(error)
  }
}

// 로그인 했으면 수행
if (loginStore.isLogin == true) {
  getBookmarkListOfCurUser(loginStore.userId)
}

// toggle event handler
const changeImageUrlHandler = (contentId) => {
  if (loginStore.isLogin == false) {
    alert('로그인 해주세요!')
    router.push('/login')
  }

  // event.target
  const curImgTag = document.getElementById(contentId)
  const imagePath = curImgTag.src.replace(window.location.origin, '')
  if (imagePath == bookMarkOFFUrl) {
    // 북마크 등록( userId, contentId(관광지) 필요 )
    console.log('userId: ', loginStore.userId)
    insertBookmark(loginStore.userId, contentId)

    curImgTag.src = bookMarkONUrl
  } else {
    // 북마크 삭제
    deleteBookmark(loginStore.userId, contentId)

    curImgTag.src = bookMarkOFFUrl
  }
}

// pagination
const movePage = (pageIndex) => {
  console.log('BoardMainVue : movePage : pageIndex : ' + pageIndex)
  setSearchMovePage(pageIndex)
  attractionList()
}
</script>
