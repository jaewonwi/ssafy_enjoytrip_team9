<template>
<div class="untree_co-section">
		<div class="container">
			<div class="row text-center justify-content-center mb-5">
				<div class="title col-lg-7"><h2 class="section-title text-center font-weight-bold">인기 여행지</h2></div>
			</div>

			<!-- tour -->
			<div class="d-inline">
				<div class="row">
				<div class="col-3">
					<h4 class="section-title text-center w-100">관광지</h4>
					<div v-if="mainSearchStore.tour && mainSearchStore.tour.length" class="owl-carousel owl-4-slider" >	
						<div class="item p-2"
							v-for="(attraction) in mainSearchStore.tour" :key="attraction.contentId" :value="attraction.contentId">
						<a class="media-thumb" :href="attraction.firstImage" data-fancybox="gallery">
							<div class="media-text p-3 w-100 h-100">
								<h3>{{ attraction.title }}</h3>
								<span class="location">{{ attraction.addr1 }}</span>
							</div>
							<img :src="attraction.firstImage" alt="Image" class="img-fluid w-100">
						</a> 
						</div>
					</div>
				</div>

				<!-- culture -->
				<div class="col-3">
					<h4 class="section-title text-center w-100">문화</h4>
					<div v-if="mainSearchStore.culture && mainSearchStore.culture.length" class="owl-carousel owl-4-slider" >
						<div class="item p-2"
							v-for="(attraction) in mainSearchStore.culture" :key="attraction.contentId" :value="attraction.contentId">
						<a class="media-thumb" :href="attraction.firstImage" data-fancybox="gallery">
							<div class="media-text p-3 w-100 h-100">
								<h3>{{ attraction.title }}</h3>
								<span class="location">{{ attraction.addr1 }}</span>
							</div>
							<img :src="attraction.firstImage" alt="Image" class="img-fluid w-100">
						</a> 
						</div>
					</div>
				</div>

				<!-- festival -->
				<div class="col-3">
					<h4 class="section-title text-center w-100">축제 공연 행사</h4>
					<div v-if="mainSearchStore.festival && mainSearchStore.festival.length" class="owl-carousel owl-4-slider" >	
						<div class="item p-2"
							v-for="(attraction) in mainSearchStore.festival" :key="attraction.contentId" :value="attraction.contentId">
						<a class="media-thumb" :href="attraction.firstImage" data-fancybox="gallery">
							<div class="media-text p-3 w-100 h-100">
								<h3>{{ attraction.title }}</h3>
								<span class="location">{{ attraction.addr1 }}</span>
							</div>
							<img :src="attraction.firstImage" alt="Image" class="img-fluid w-100">
						</a> 
						</div>
					</div>
				</div>

				<!-- leisure -->
				<div class="col-3">
					<h4 class="section-title text-center w-100">레저</h4>
					<div v-if="mainSearchStore.leisure && mainSearchStore.leisure.length" class="owl-carousel owl-4-slider" >	
						<div class="item p-2" v-for="(attraction) in mainSearchStore.leisure" :key="attraction.contentId" :value="attraction.contentId">
						<a class="media-thumb" :href="attraction.firstImage" data-fancybox="gallery">
							<div class="media-text p-3 w-100 h-100">
								<h3>{{ attraction.title }}</h3>
								<span class="location">{{ attraction.addr1 }}</span>
							</div>
							<img :src="attraction.firstImage" alt="Image" class="img-fluid w-100">
						</a> 
						</div>
					</div>
				</div>



				</div>
			</div>
				
		</div>
	</div>
</template>

<script setup>
	// basic
	import { ref, onMounted, onUpdated } from 'vue'
	import http from '@/common/axios.js'

	// store
	import { useSearchStore } from '@/stores/searchStore'
	import { useBookmarkStore } from '@/stores/bookmarkStore'
	import { useLoginStore } from '@/stores/loginStore'

	// router
	import { useRouter } from 'vue-router'

	
	const { searchStore, mainSearchStore, attractionMainList } = useSearchStore()

	attractionMainList();

	// A $( document ).ready() block.
	onMounted(() => {
		$(document).ready(function () {
		$('.owl-carousel').owlCarousel({
			autoplay: true,
			smartSpeed: 1000,
			items: 1,
			loop: true,
			dots: true,
			nav: false
		})
	})
	})
	onUpdated(() => {
		$(document).ready(function () {
		$('.owl-carousel').owlCarousel({
			autoplay: true,
			smartSpeed: 1000,
			items: 1,
			loop: true,
			dots: true,
			nav: false
		})
	})
	})
</script>

<style scoped>
img{
 height: 250px;
}
.media-thumb .media-text {
	top: 90px;
	left: 0px;
	margin-top: 0px;
	text-align: center;
}
</style>