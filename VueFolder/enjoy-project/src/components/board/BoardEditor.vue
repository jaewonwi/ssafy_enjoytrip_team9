<template>
    <div class="container col-12 mt-3">
        <span>제목: </span><input type="text" v-model="editorDataTitle">
        <div class="mt-3">
            <ckeditor :editor="editor" v-model="editorDataContent" :config="editorConfig"></ckeditor>
            <button type="button" class="btn btn-primary mt-2" @click="insertBoard">등록</button>
            <button type="button" class="btn btn-primary mt-2" @click="updateBoard">수정</button>        
        </div>
    </div>
</template>



<script setup>
import { ref } from 'vue';
import CKEditor from '@ckeditor/ckeditor5-vue';
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import router from '../../router';

const ckeditor = CKEditor.component
const editor = ClassicEditor

const editorDataTitle = ref('')
const editorDataContent = ref('<p>내용을 입력해주세요.<p>')
const editorConfig = {}

const insertBoard = () => {
    console.log(editorDataTitle.value)
    console.log(editorDataContent.value)

    router.push({
        path: '/board'
    })
}

const updateBoard = () => {
    alert(editorDataContent.value)
  
    router.push({
        path: '/board'
    })
}
</script>



<style scoped>

/* deep selector >>> -> 함수 deep() */
.container:deep(.ck-editor__editable) {
  height: 400px;
}

</style>