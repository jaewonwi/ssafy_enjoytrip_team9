<template>
    <!-- search -->
    <div class="input-group col-12">
        <input v-model="boardStore.searchWord"  @keydown.enter="boardList" type="text" class="form-control" 
            style="border-radius: 10px;" placeholder="Search">
        &nbsp; 
        <button @click="boardList" class="btn btn-primary rounded-3" type="button">Search</button>
    </div>

    <!-- board List -->
    <div class="col-12 mt-4">
        <div class="table-responsive rounded-4">
            <table class="text-nowrap mb-0 table">
                <thead class="table-light">
                    <tr>
                        <th>#</th>
                        <th>제목</th>
                        <th>작성자</th>
                        <th>등록일</th>
                        <th>조회수</th>
                        <!-- <th>좋아요</th> -->
                        <th v-if="isManager">글삭제</th>    <!-- 관리자 전용 -->
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="(board) in boardStore.list" v-bind:key="board.boardId" @click.stop="boardDetail(board.boardId)">
                        <td>{{ board.boardId }}</td>
                        <td>{{ board.boardTitle }}</td>
                        <td>{{ board.userNm }}</td>
                        <td>{{ util.makeDateStr(board.boardRegDate.date, '.') }}</td>
                        <td>{{ board.boardReadCount }}</td>
                        <!-- <td>{{ board.boardLike }}</td> -->
                        <td v-if="isManager">    <!-- 관리자 전용 -->
                            <button class="btn btn-primary btn-sm" @click.stop="deleteBoard(board.boardId)">삭제</button>
                        </td>
                    </tr>
                </tbody>
            </table>
            
            <div class="divider m-5"></div>

            <div class="row m-4 d-flex justify-content-end">
                <button v-if="isManager" @click="goEditor" class="btn btn-danger col-2">공지사항 등록하기</button>    
                <button v-else @click="goEditor" class="btn btn-primary col-1">글쓰기</button>                
            </div>
            
            <PaginationUI v-on:call-parent="movePage"></PaginationUI>
            
        </div>
    </div>
</template>

<script setup>
    // const props = defineProps(['boardList']);
    // basic
    import { ref, reactive, onMounted } from 'vue';

    // common
    import http from '@/common/axios.js'
    import util from '@/common/util.js'

    // router
    import { useRouter } from 'vue-router';

    // store
    import { useBoardStore } from '@/stores/boardStore'
    import { useLoginStore } from '@/stores/loginStore'

    // component
    import PaginationUI from "@/components/board/PaginationUI.vue";

    const router = useRouter();
    
    const { boardStore, boardList, setBoardMovePage } = useBoardStore();
    const { loginStore } = useLoginStore();

    const boardDetail = async (boardId) => {
        try {
            router.push({
                name: 'BoardDetail',
                params: { boardId: boardId }
            })
        } catch (error) {
            console.log("BoardMainVue: error : ");
            console.log(error);
        }
    }

    // 관리자 여부
    let isManager = ref(false)

    // 초기 작업
    boardStore.searchWord = ''
    boardList();
    // console.log("login : ")  // login 확인
    // console.log(loginStore)
    // console.log(loginStore.userClsf)

    
    if (loginStore.userClsf == '001'){
        isManager.value = true
    }

    // pagination
    const movePage= (pageIndex) => {
        console.log("BoardMainVue : movePage : pageIndex : " + pageIndex);
        setBoardMovePage(pageIndex);
        boardList();
    }

    const goEditor = () => router.push('board/editor')

    const deleteBoard = async (boardId) => {
        let result = confirm("해당 게시글을 삭제하시겠습니까?")
        if (result) {
            try {
                let { data } = await http.delete('/boards/'+boardId)
                console.log(data)
                boardList();
                // router.push('/board')
            } catch (error) {
                console.log(error)
            }
        }
    }
</script>

<style scoped>
</style>