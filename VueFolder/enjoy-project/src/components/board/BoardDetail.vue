<template>
    <!-- <div class="col-12 mt-3 p-3" style="border: solid 3px;"> -->
    
    <div>
        <!-- detail head -->
        <div class="row detail-head bg-light p-3 rounded-2">
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
                            <span class="icon-eye"></span> {{ board.boardViewCnt }} &nbsp;
                            <span class="icon-heart-o me-2"></span> {{ board.boardLike }} &nbsp;
                            <span class="icon-commenting-o me-2"></span> 0
                        </span>
                    </div>
                    <br /><br /><br /><br /><br /><br /><br />
                    {{ board }} <br>{{ board.boardRegDate }}<br> 
                </div>
            </div>
        </div>

        <!-- content -->
        <div class="row">
            <div class="text-secondary">
                {{ board.boardContent }}
            </div>
            <div class="divider my-5"></div>
            <div class="d-flex flex-row-reverse">
                <button type="button" class="btn btn-outline-danger mb-3 ms-1" @click="deleteBoard">
                    글삭제 </button>
                <button type="button" class="btn btn-outline-success mb-3 ms-1" @click="moveModify">
                    글수정 </button>
                <button type="button" class="btn btn-outline-primary mb-3" @click="moveList">
                    글목록 </button>
            </div>
        </div>
        <br>
        <!-- {{ board.boardRegDate.date }} -->
    </div>
    
</template>

<script setup>
    import axios from '@/common/axios.js';
    import { ref } from 'vue';
    import { useRoute, useRouter } from 'vue-router';
    
    // common
    import util from '@/common/util.js'

    const route = useRoute();
    const router = useRouter();
    
    const boardId = route.params.boardId;
    console.log(boardId)

    const board = ref({});

    const getDetail = async () => {
        console.log('0-000000000000000000000000000000000000000000000')
        console.log(boardId)
        try {
            let { data } = await axios.get('/boards/'+boardId);
            board.value = data;
            console.log("getdetail....")
            console.log(data)
            console.log(board.value)
        } catch (error) {
            console.error(error);
        }
    }

    const moveList = () => router.push('/board')
    const moveModify = () => {
        // 로그인된 사용자가 쓴 글이라면
        try {
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