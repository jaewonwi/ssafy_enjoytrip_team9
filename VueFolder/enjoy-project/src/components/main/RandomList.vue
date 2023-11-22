<template>
<div class="untree_co-section">
		<div class="container">
			<div class="row text-center justify-content-center mb-5">
				<div class="col-lg-7"><h2 class="section-title text-center">Popular Destination</h2></div>
			</div>

			<div class="owl-carousel owl-3-slider">

				<!-- <div v-for="(attraction) in searchStore.list" :key="attraction.contentId" :value="attraction.contentId"> -->
					<div class="item" v-for="(attraction) in searchStore.list" :key="attraction.contentId" :value="attraction.contentId">
						<a class="media-thumb" href="images/hero-slider-1.jpg" data-fancybox="gallery">
							<div class="media-text">
								<h3>{{ attraction.title }}</h3>
								<span class="location">Italy</span>
							</div>
							<img :src="attraction.firstImage" alt="Image" class="img-fluid">
						</a> 
					</div>
				<!-- </div> -->

				<div class="item">
					<a class="media-thumb" href="images/hero-slider-1.jpg" data-fancybox="gallery">
						<div class="media-text">
							<h3>Pragser Wildsee</h3>
							<span class="location">Italy</span>
						</div>
						<img src="images/hero-slider-1.jpg" alt="Image" class="img-fluid">
					</a> 
				</div>

				<div class="item">
					<a class="media-thumb" href="images/hero-slider-2.jpg" data-fancybox="gallery">
						<div class="media-text">
							<h3>Oia</h3>
							<span class="location">Greece</span>
						</div>
						<img src="images/hero-slider-2.jpg" alt="Image" class="img-fluid">
					</a> 
				</div>

			</div>

		</div>
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

	
	const { searchStore, getSidoList, getGugunList, attractionList, attractionRandomList } = useSearchStore()

	attractionRandomList();

</script>