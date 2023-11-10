<template>
    <div class="col-md-12 col-12 px-5">
        <div class="table-responsive">
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
        </div>
    </div>
</template>

<script setup>
    const props = defineProps(['boardList']);
    const emit = defineEmits(['call-parent-detail'])
    const detail = (boardId) => emit('call-parent-detail', boardId);
</script>

<style scoped>
</style>