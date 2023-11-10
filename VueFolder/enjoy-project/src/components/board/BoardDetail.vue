<template>
    <div class="col-12 p-3" style="border: solid 3px;">
    <h1>Board Detail</h1>
    
    <div v-bind="boardDetailData">
        {{ boardDetailData.boardId }}
        {{ boardDetailData.boardTitle }}
        {{ boardDetailData.boardContent }}
        {{ boardDetailData.boardRegDate }}
        {{ boardDetailData.boardLike }}
        {{ boardDetailData.userId }}
        {{ boardDetailData.userEmail }}
        {{ boardDetailData.userNm }}
        {{ boardDetailData.userProfile }}
    </div>
    
</div>
</template>

<script setup>
    const props = defineProps(['boardDetailData']);

</script>

<style>
</style>