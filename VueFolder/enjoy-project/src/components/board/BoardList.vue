<template>
    <!-- search -->
    <div class="input-group col-12">
        <input id="inputSearchWord" type="text" class="form-control" style="border-radius: 10px;" placeholder="Search">
        <button id="btnSearchWord" class="btn btn-primary" type="button">Search</button>
    </div>

    <!-- board List -->
    <div class="col-12 mt-3">
        <div class="table-responsive rounded-4">
            <table class="text-nowrap mb-0 table">
                <thead class="table-light">
                    <tr>
                        <th>#</th>
                        <th>제목</th>
                        <th>작성자</th>
                        <th>등록일</th>
                        <th>조회수</th>
                        <th>좋아요</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="(board, index) in boardList" v-bind:key="index" @click="detail(board.boardId)">
                        <td>{{board.boardId}}</td>
                        <td>{{board.boardTitle}}</td>
                        <td>{{board.userNm}}</td>
                        <td>{{board.boardRegDate}}</td>
                        <td>{{board.boardViewCnt}}</td>
                        <td>{{board.boardLike}}</td>
                    </tr>
                </tbody>
            </table>
            
		    <div id="paginationWrapper"></div>

            
            <div class="row m-4 justify-content-end">
                <div class="mx-4">
                    <button class="btn btn-sm btn-primary" id="btnBoardInsertUI" >글쓰기</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
    // const props = defineProps(['boardList']);
    import axios from 'axios';
    import { ref } from 'vue';
    import { useRouter } from 'vue-router';

    const router = useRouter();
    const emit = defineEmits(['call-parent-detail'])
    const detail = (boardId) => {
        emit('call-parent-detail', boardId);

        console.log(boardId)
        router.push({
            name: 'BoardDetail',
            params: { boardId: boardId}
        })
    }

    const boardList = ref([]);

    const list = async () => {
        try {
            let response = await axios.get('http://localhost:8080/boards');
            // console.log(response);
            let { data } = response;
            console.log(data);
            boardList.value = data;
        } catch (error) {
            console.error(error);
        }
    }

    list();

</script>

<style scoped>
</style>