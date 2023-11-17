<template>
    <div class="container col-12 mt-3">
        <span>제목: </span><input type="text" v-model="editorDataTitle">
        <div class="mt-3">
            <ckeditor :editor="editor" v-model="editorDataContent" :config="editorConfig"></ckeditor>
            <button type="button" class="btn btn-primary mt-2" @click="insertBoard">등록</button>
            <button type="button" class="btn btn-primary mt-2" @click="updateBoard">수정</button>        
        </div>
    </div>

    <div class="modal fade" id="insertModal">
      <div class="modal-dialog modal-lg">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">글 쓰기</h5>
            <button
              type="button"
              class="btn-close"
              data-bs-dismiss="modal"
              aria-label="Close"
            ></button>
          </div>
          <div class="modal-body">
            <div class="mb-3">
              <input type="text" v-model="title" class="form-control" placeholder="제목" />
            </div>
            <div class="mb-3">
              <ckeditor :editor="editor" v-model="editorData" :config="editorConfig"></ckeditor>
            </div>
            <div class="form-check mb-3">
              <input
                v-model="attachFile"
                class="form-check-input"
                type="checkbox"
                value=""
                id="chkFileUploadInsert"
              />
              <label class="form-check-label" for="chkFileUploadInsert">파일 추가</label>
            </div>
            <div class="mb-3" v-show="attachFile" id="imgFileUploadInsertWrapper">
              <input @change="changeFile" type="file" id="inputFileUploadInsert" multiple />
              <div id="imgFileUploadInsertThumbnail" class="thumbnail-wrapper">
                <!-- vue way img 를 만들어서 append 하지 않고, v-for 로 처리 -->
                <img v-for="(file, index) in fileList" v-bind:src="file" v-bind:key="index" />
              </div>
            </div>
            <button class="btn btn-sm btn-primary btn-outline float-end" data-bs-dismiss="modal" type="button" @click="boardInsert" >
              등록
            </button>
          </div>
        </div>
      </div>
    </div>
</template>



<script setup>
import { ref } from 'vue';
import { useRoute, useRouter } from 'vue-router';

import axios from '@/common/axios.js';
import CKEditor from '@ckeditor/ckeditor5-vue';
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';

const ckeditor = CKEditor.component
const editor = ClassicEditor

const route = useRoute();
const router = useRouter();

const editorDataTitle = ref('')
const editorDataContent = ref('<p>내용을 입력해주세요.<p>')
const editorConfig = {}

const boardId = route.params.boardId;   // 아이디가 있다면 아이디를 받아오자

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