<template>
	<div class="hero hero-inner">
		<div class="container">
			<div class="row align-items-center">
				<div class="col-lg-6 mx-auto text-center">
					<div class="intro-wrap">
						<h1 class="mb-0">게시판</h1>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="row justify-content-center">
		<div class="untree_co-section col-10">
			<router-view></router-view>
		</div>
	</div>
	
</template>

<script setup>
    
    import { ref } from 'vue';
	import { RouterView } from 'vue-router'
    import BoardList from '../components/board/BoardList.vue';

    // const board = ref({});
    // const boardList = ref([]);

    // const list = async () => {
    //     try {
    //         let response = await axios.get('http://localhost:8080/boards');
    //         console.log(response);
    //         let { data } = response;
    //         console.log(data);
    //         boardList.value = data;
    //     } catch (error) {
    //         console.error(error);
    //     }
    // }

	const props = defineProps(['call-parent-detail']);

    // const detail = async (boardId) => {
    //     try {
    //         let { data } = await axios.get('http://localhost:8080/boards/'+boardId);
    //         board.value = data;
	// 		router.push({
	// 			path: `/board/detail/${boardId}`
	// 		})
    //     } catch (error) {
    //         console.error(error);
    //     }
    // }

    // list();
</script>

