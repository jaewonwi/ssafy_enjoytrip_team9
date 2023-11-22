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
          <p>{{ searchStore.overview }}</p>
          <ul class="list-unstyled clearfix">
            <li>💛 &nbsp;주소: {{ searchStore.addr1 }} {{ searchStore.addr2 }}</li>  
          </ul>
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
  console.log("contentId : "+contentId)

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