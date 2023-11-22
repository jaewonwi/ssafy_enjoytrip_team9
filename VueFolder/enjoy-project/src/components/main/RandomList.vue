<template>
<div class="untree_co-section">
		<div class="container">
			<div class="row text-center justify-content-center mb-5">
				<div class="title col-lg-7"><h2 class="section-title text-center">여기는 어떠세요?</h2></div>
			</div>

			<!-- tour -->
			<div class="d-inline">
				<div class="row">

					<div v-if="mainSearchStore.tour && mainSearchStore.tour.length" class="col-3 owl-carousel owl-4-slider w-25" >	
					<div class="item p-2"
						v-for="(attraction) in mainSearchStore.tour" :key="attraction.contentId" :value="attraction.contentId">
					<a class="media-thumb" :href="attraction.firstImage" data-fancybox="gallery">
						<div class="media-text p-3">
							<h3>{{ attraction.title }}</h3>
							<span class="location">{{ attraction.addr1 }}</span>
						</div>
						<img :src="attraction.firstImage" alt="Image" class="img-fluid w-100">
					</a> 
					</div>
				</div>

				<!-- culture -->
				<div v-if="mainSearchStore.culture && mainSearchStore.culture.length" class="col-3 owl-carousel owl-4-slider w-25" >	
					<div class="item p-2"
						v-for="(attraction) in mainSearchStore.culture" :key="attraction.contentId" :value="attraction.contentId">
					<a class="media-thumb" :href="attraction.firstImage" data-fancybox="gallery">
						<div class="media-text p-3">
							<h3>{{ attraction.title }}</h3>
							<span class="location">{{ attraction.addr1 }}</span>
						</div>
						<img :src="attraction.firstImage" alt="Image" class="img-fluid w-100">
					</a> 
					</div>
				</div>
				<!-- festival -->
				<div v-if="mainSearchStore.festival && mainSearchStore.festival.length" class="col-3 owl-carousel owl-4-slider w-25" >	
					<div class="item p-2"
						v-for="(attraction) in mainSearchStore.festival" :key="attraction.contentId" :value="attraction.contentId">
					<a class="media-thumb" :href="attraction.firstImage" data-fancybox="gallery">
						<div class="media-text p-3">
							<h3>{{ attraction.title }}</h3>
							<span class="location">{{ attraction.addr1 }}</span>
						</div>
						<img :src="attraction.firstImage" alt="Image" class="img-fluid w-100">
					</a> 
					</div>
				</div>

				<!-- leisure -->
				<div v-if="mainSearchStore.leisure && mainSearchStore.leisure.length" class="col-3 owl-carousel owl-4-slider w-25" >	
					<div class="item p-2"
						v-for="(attraction) in mainSearchStore.leisure" :key="attraction.contentId" :value="attraction.contentId">
					<a class="media-thumb" :href="attraction.firstImage" data-fancybox="gallery">
						<div class="media-text p-3">
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
</style>