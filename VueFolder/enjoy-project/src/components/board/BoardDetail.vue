<template>
    <!-- <div class="col-12 mt-3 p-3" style="border: solid 3px;"> -->
    
    <div class="container">
        <!-- detail head -->
        <div class="row detail-head">
            <!-- title -->
            <div class="row">
                <h2 class="title">{{ board.boardId }}. {{ board.boardTitle }}</h2>  
            </div>
            <!-- profile -->
            <div class="row">
                <div class="clearfix align-start">
                    <img :src="board.userProfile"
                        class="avatar d-inline-block me-4 float-md-start rounded-circle img-thumbnail" />
                    <div class="user-info">
                        <span class="fw-bold">{{ board.userNm }}</span> <br />
                        <span class="text-secondary fw-light">
                            {{ board.boardRegDate }} <br /> 
                            조회 : {{ board.boardViewCnt }} 좋아요 : {{ board.boardLike }} 댓글 : 0
                        </span>
                    </div>
                </div>
            </div>
        </div>
        
        <!-- content -->
        <hr>     
        <div class="row">
            <div class="col-lg-10 text-start">
                <div class="row">
                    <div class="divider mb-3"></div>
                    <div class="text-secondary">
                        {{ board.boardContent }}
                    </div>
                    <div class="divider mt-3 mb-3"></div>
                </div>

                <div class="row mt-5">
                    <div class="d-flex justify-content-end">
                    <button type="button" class="btn btn-outline-primary mb-3" @click="moveList">
                        글목록 </button>
                    <button type="button" class="btn btn-outline-success mb-3 ms-1" @click="moveModify">
                        글수정 </button>
                    <button type="button" class="btn btn-outline-danger mb-3 ms-1" @click="onDeleteArticle">
                        글삭제 </button>
                    </div>
                </div>
            </div>

            <!-- <div class="col-lg-5 pl-lg-5 ml-auto">
                <h2 class="section-title mb-4">{{ boardDetailData.boardTitle }}</h2>
                <p>{{ boardDetailData.boardContent }}</p>
                <ul class="list-unstyled two-col clearfix">
                    <li>{{ boardDetailData.userEmail }}</li>
                    <li>{{ boardDetailData.userNm }}</li>
                </ul>
            </div> -->
        </div>
        
    </div>
    
</template>

<script setup>
    import axios from 'axios';
    import { ref } from 'vue';
    import { useRoute, useRouter } from 'vue-router';
    // import util from '@/common/util.js'

    const route = useRoute();
    
    const boardId = route.params.boardId;
    console.log(boardId)

    const board = ref({});

    const getDetail = async () => {
        try {
            let { data } = await axios.get('http://localhost:8080/boards/'+boardId);
            board.value = data;
            console.log(data)
        } catch (error) {
            console.error(error);
        }
    }
    
    getDetail();
</script>

<style scoped>
.detail-head {
    height: 200px;
}
.detail-head .avatar {
    height: 80px;
} 
</style>