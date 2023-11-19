<template>
    <!-- search -->
    <div class="input-group col-12">
        <input v-model="boardStore.searchWord"  @keydown.enter="boardList" type="text" class="form-control" 
            style="border-radius: 10px;" placeholder="Search">
        &nbsp;
        <button @click="boardList" class="btn btn-primary rounded-3" type="button">Search</button>
    </div>
    
    <!-- board List
    <board-list></board-list> -->

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
                    <tr v-for="(board) in boardStore.list" v-bind:key="board.boardId" @click="boardDetail(board.boardId)">
                        <td>{{ board.boardId }}</td>
                        <td>{{ board.boardTitle }}</td>
                        <td>{{ board.userNm }}</td>
                        <td>{{ util.makeDateStr(board.boardRegDate.date, '.') }}</td>
                        <td>{{ board.boardReadCount }}</td>
                        <td>{{ board.boardLike }}</td>
                    </tr>
                </tbody>
            </table>
            
            <PaginationUI v-on:call-parent="movePage"></PaginationUI>
            
            <div class="row m-4 justify-content-end">
                <div class="mx-4">
                    <button @click="goEditor" class="btn btn-primary" >글쓰기</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
    // const props = defineProps(['boardList']);
    // basic
    import { ref, onMounted } from 'vue';

    // common
    import http from '@/common/axios.js'
    import util from '@/common/util.js'

    // router
    import { useRouter } from 'vue-router';

    // store
    import { useBoardStore } from '@/stores/boardStore'
    // import { useAuthStore } from '@/stores/authStore'


    const router = useRouter();
    
    const { boardStore, boardList, setBoardMovePage, setBoardDetail } = useBoardStore();
    // const { authStore } = useAuthStore()

    const boardDetail = async (boardId) => {
        try {
            // let { data } = await http.get("/boards/" + boardId);

            // if (data.result == "login") {
            //     doLogout();
            // } else {
                // let { dto } = data;
                // setBoardDetail(dto);
                router.push({
                    name: 'BoardDetail',
                    params: { boardId: boardId }
                })
            // }
        } catch (error) {
            console.log("BoardMainVue: error : ");
            console.log(error);
        }
    }

    // 초기 작업
    boardStore.searchWord = ''
    boardList();

    // pagination
    const movePage= (pageIndex) => {
        console.log("BoardMainVue : movePage : pageIndex : " + pageIndex);
        setBoardMovePage(pageIndex);
        boardList();
    }

    const goEditor = () => router.push('board/editor')

</script>

<style scoped>
</style>