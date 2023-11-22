<template>
  <div class="untree_co-section">
    <div class="container">
      <div class="row text-center justify-content-center mb-5">
        <div class="col-lg-7"><h2 class="section-title text-center">북마크</h2></div>
      </div>

      <div v-for="attraction in store.searchStore.list" :key="attraction.contentId" class="col-6 col-md-6 col-lg-3">
        <div class="media-1 position-relative">
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
              <span class="">{{ attraction.title }}</span>
              <p>{{ attraction.addr1 }}</p>
            </div>
          </div>
        </div>
      </div>
      <!--  -->
    </div>
  </div>
</template>

<script setup>
import { ref, reactive } from 'vue'
import { useBookmarkStore } from '@/stores/bookmarkStore'
import { useLoginStore } from '@/stores/loginStore'

const { bookmarkStore, insertBookmark, deleteBookmark } = useBookmarkStore()
const { loginStore } = useLoginStore()
</script>
