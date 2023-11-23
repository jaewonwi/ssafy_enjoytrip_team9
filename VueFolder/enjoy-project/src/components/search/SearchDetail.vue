<template>
  <div class="untree_co-section">
    <div class="container">
      <div class="row">
        <div class="col-lg-7 mb-3">
          <div class="">
            <img v-if="searchStore.firstImage" :src="searchStore.firstImage" class="img-fluid rounded-20 w-100">
            <img v-else src="@/assets/noImage.png" class="img-fluid rounded-20 w-100">
          </div>
        </div>
        
        <div class="col-lg-5 pl-lg-5 ml-auto">
          <h2 class="section-title mb-4">{{ searchStore.title }}</h2>
          <p class="text-secondary">{{ searchStore.overview }}</p>
          <ul class="list-unstyled clearfix">
            <li>💛 &nbsp;주소: {{ searchStore.addr1 }} {{ searchStore.addr2 }}
              <span v-if="searchStore.zipcode">( 우편번호: {{ searchStore.zipcode }} )</span>
            </li>
            <li v-if="searchStore.tel">💛 &nbsp;전화: {{ searchStore.tel }} </li>
            
          </ul>
          <div class="divider m-5"></div>
          <a class="btn btn-primary ml-auto"
          :href=" 'https://map.kakao.com/link/search/' + searchStore.title "
          >카카오맵 이동</a>
          
        </div>

        

      </div>
    </div>
  </div>

</template>

<script setup>
  import http from '@/common/axios.js'
  import { computed, ref } from 'vue'
  import { useRoute, useRouter } from 'vue-router';

// store
import { useSearchStore } from '@/stores/searchStore'

  // route, router
  const route = useRoute();
  const router = useRouter();

  const { searchStore, setSearchAttractionDetail }  = useSearchStore()

  const contentId = route.params.contentId
  // console.log("contentId : "+contentId)
  // console.log("contentTypeId : "+searchStore.contentTypeId)

  const getDetail = async () => {
    try {
      let { data } = await http.get('/search/detail/'+contentId);

      console.log("get Detail : ")
      console.log(data);

      setSearchAttractionDetail(data)

    } catch (error) {
      console.log(error)
    }
  }

  getDetail()

</script>