<template>
  <hr />
  <div class="untree_co-section">
    <div class="container">
      <div class="row text-center justify-content-center mb-5">
        <div class="col-lg-7"><h2 class="section-title text-center">북마크</h2></div>
      </div>

      <div class="row">
        <div v-for="attraction in bookmarkStore.viewBookmarkList" :key="attraction.contentId" class="col-6 col-md-6 col-lg-3" @click="attractionDetail(attraction.contentId)">
          <div class="media-1 position-relative">
            <img
              class="position-absolute m-2 rounded-0"
              type="checkbox"
              style="max-width: 50px; max-height: 40px; pointer-events: auto"
              :id="attraction.contentId"
              :src="bookMarkONUrl"
              @click.stop="changeImageUrlHandler(attraction.contentId)"
            />
            <a href="#" class="d-block mb-3 z-2">
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
      <!--  -->
    </div>
  </div>
</template>

<script setup>
// 카드 형식으로 보여주기 위해서 가져와야 할 데이터: contentId, title, addr1, firstImage

import { ref, reactive } from 'vue'
import http from '@/common/axios.js'

import bookMarkOFFUrl from '/src/assets/bookmark/bookmarkOFF.png'
import bookMarkONUrl from '/src/assets/bookmark/bookmarkON.png'
import noImageUrl from '/src/assets/noImage.png'

import { useBookmarkStore } from '@/stores/bookmarkStore'
import { useLoginStore } from '@/stores/loginStore'
import { useSearchStore } from '@/stores/searchStore'

// router
import { useRouter } from 'vue-router'

const { bookmarkStore, deleteBookmark, getBookmarkList, getBookmarkListOfCurUser } = useBookmarkStore()
const { loginStore } = useLoginStore()
const { searchStore, getSidoList, getGugunList, attractionList } = useSearchStore()

const router = useRouter()

const attractionDetail = async (contentId) => {
  try {
    router.push({
      name: 'SearchAttractionDetail',
      params: { contentId }
    })
  } catch (error) {
    console.log('attractionDetailVue: error: ')
    console.log(error)
  }
}

// toggle event handler
const changeImageUrlHandler = (contentId) => {
  // event.target
  const curImgTag = document.getElementById(contentId)
  const imagePath = curImgTag.src.replace(window.location.origin, '') // http://localhost:5173/ 까지 제거
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

getBookmarkList(loginStore.userId)
getBookmarkListOfCurUser(loginStore.userId)
</script>
