<template>
    <div class="col-12 mt-3 p-3" style="border: solid 3px;">
        <h1>Board Detail</h1>
        
        <div v-bind="board">
            {{ board.boardId }}
            {{ board.boardTitle }}
            {{ board.boardContent }}
            {{ board.boardRegDate }}
            {{ board.boardLike }}
            {{ board.userId }}
            {{ board.userEmail }}
            {{ board.userNm }}
            {{ board.userProfile }}
        </div>
        
        <div class="untree_co-section">
            <div class="container">
            <div class="row">
            <div class="col-lg-7">
                <div class="owl-single dots-absolute owl-carousel">
                    <img src="images/slider-1.jpg" alt="Free HTML Template by Untree.co" class="img-fluid rounded-20">
                    <img src="images/slider-2.jpg" alt="Free HTML Template by Untree.co" class="img-fluid rounded-20">
                    <img src="images/slider-3.jpg" alt="Free HTML Template by Untree.co" class="img-fluid rounded-20">
                    <img src="images/slider-4.jpg" alt="Free HTML Template by Untree.co" class="img-fluid rounded-20">
                    <img src="images/slider-5.jpg" alt="Free HTML Template by Untree.co" class="img-fluid rounded-20">
                </div>
            </div>
            <div class="col-lg-5 pl-lg-5 ml-auto">
                <h2 class="section-title mb-4">{{ boardDetailData.boardTitle }}</h2>
                <p>{{ boardDetailData.boardContent }}</p>
                <ul class="list-unstyled two-col clearfix">
                    <li>{{ boardDetailData.userEmail }}</li>
                    <li>{{ boardDetailData.userNm }}</li>
                </ul>
            </div>
        </div>
            </div>
    </div>
</div>
</template>

<script setup>
    import axios from 'axios';
    import { ref } from 'vue';
    import { useRoute, useRouter } from 'vue-router';

    const route = useRoute();
    const boardId = ref();
    console.log("dddddddd"+boardId.value);
    boardId.value = route.query.boardId;
    
    const board = ref({});

    const detail = async (boardId) => {
        try {
            let { data } = await axios.get('http://localhost:8080/boards/'+boardId);
            board.value = data;
        } catch (error) {
            console.error(error);
        }
    }

    detail(boardId);
</script>

<style>
</style>