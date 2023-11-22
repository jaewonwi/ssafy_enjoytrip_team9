<template>
    <hr />
    <div class="untree_co-section">
        <div class="container">
            <div class="row text-center justify-content-center mb-5">
                <div class="col-lg-7"><h2 class="section-title text-center">사용자 탈퇴</h2></div>
            </div>
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



    </div>
</template>