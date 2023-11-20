<template>
    <!-- <div class="col-12 mt-3 p-3" style="border: solid 3px;"> -->
    
    <div>
        <!-- detail head -->
        <div class="row detail-head p-3 rounded-2">
            <!-- title -->
            <div class="row">
                <h2 class="title">{{ board.boardId }}. {{ board.boardTitle }}</h2>  
            </div>
            <!-- profile -->
            <div class="row">
                <div class="align-start">
                    <img :src="board.userProfile"
                        class="avatar d-inline-block me-4 float-md-start rounded-circle img-thumbnail" />
                    <div class="user-info">
                        <span class="fw-bold">{{ board.userNm }}</span> <br />
                        <span class="text-secondary fw-light">
                            <!-- {{ util.makeDateStr(board.boardRegDate.date, '.') }} &nbsp; -->
                            <span class="icon-eye"></span> {{ board.boardReadCount }} &nbsp;
                            <span class="icon-heart-o me-2"></span> {{ board.boardLike }} &nbsp;
                            <span class="icon-commenting-o me-2"></span> 0
                        </span>
                    </div>
                </div>
            </div>
        </div>

        <!-- content -->
        <div class="row">
            <div class="text-secondary bg-light rounded-3">
                <div class="p-4" v-html="board.boardContent"></div>
                <div class="divider mt-5"></div>
                <!-- <div class="d-flex flex-row-reverse">
                    <button class="btn btn-outline-none" @click="clickLike">좋아요</button>
                    <span class="fs-3 fw-bold icon-heart my-3" v-show="isLiked"></span>
                    <span class="fs-3 fw-bold icon-heart-o my-3" v-show="!isLiked"></span>
                </div> -->
            </div>
            <div class="divider mt-5"></div>
            <div class="d-flex flex-row-reverse">
                <button type="button" class="btn btn-outline-danger mb-3 ms-1" @click="deleteBoard" v-show="sameUser">
                    글삭제 </button> &nbsp;
                <button type="button" class="btn btn-outline-success mb-3 ms-1" @click="moveModify" v-show="sameUser">
                    글수정 </button> &nbsp;
                <button type="button" class="btn btn-outline-primary mb-3" @click="moveList">
                    글목록 </button>
            </div>
        </div>
        <br>
    </div>
    
</template>

<script setup>
    import http from '@/common/axios.js';
    import { ref } from 'vue';
    import { useRoute, useRouter } from 'vue-router';
    
    // common
    import util from '@/common/util.js'

    // store
    import { useLoginStore } from '../../stores/loginStore';

    const route = useRoute();
    const router = useRouter();

    const { loginStore } = useLoginStore();

    
    const boardId = route.params.boardId;
    console.log("boardId : "+boardId)

    const board = ref({});

    let sameUser = false;
    // let isLiked = ref(false);
    
    const getDetail = async () => {
        try {
            let { data } = await http.get('/boards/'+boardId);
            board.value = data.dto;
            console.log("getDetail : ")
            console.log(data.dto)

            if (data.dto.userId == loginStore.userId ){
                console.log('작성자와 로그인한 사용자가 일치합니다.')
                sameUser = true;
            } else {
                console.log('작성자와 로그인한 사용자가 일치하지 않습니다.')
            }

        } catch (error) {
            console.error(error);
        }
    }

    const moveList = () => router.push('/board')
    const moveModify = () => {
        // 로그인된 사용자가 쓴 글이라면
        try {
            router.push({
                name: 'BoardEditor',
                params: { boardId: boardId }
            })
            console.log(boardId)
        } catch (error) {
            console.log(error)
        }
    }

    const deleteBoard = async () => {
        let result = confirm("정말로 삭제하시겠습니까?")
        console.log(result)
        if (result) {
            try {
                let { data } = await axios.delete('/boards/'+boardId)
                console.log(data)
                router.push('/board')
            } catch (error) {
                console.log(error)
            }
        }
    }

    // const clickLike = async () => {
    //     console.log("좋아용");
    //     console.log(isLiked);
    //     isLiked = !isLiked
    // }



    getDetail();

</script>

<style scoped>
.detail-head {
    height: 200px;
}
.detail-head .avatar {
    height: 90px;
} 
</style>