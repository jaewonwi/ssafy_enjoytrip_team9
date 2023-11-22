<template>
    <div class="container col-12 mt-3">
        <div class="mb-3">
          <input type="text" class="form-control" v-model="editorDataTitle" placeholder="제목을 입력해주세요.">
        </div>
        <div>
            <ckeditor :editor="editor" v-model="editorDataContent" :config="editorConfig"></ckeditor>
            <div class="divider my-2"></div>
            <button type="button" class="btn btn-primary mt-2" @click="insertBoard" v-show="!boardType">등록</button>
            <button type="button" class="btn btn-primary mt-2" @click="updateBoard" v-show="boardType">수정</button>        
        </div>
    </div>
</template>



<script setup>
import { ref, onMounted } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import http from "@/common/axios.js";

// ckeditor
import CKEditor from '@ckeditor/ckeditor5-vue';
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';

// store
// import { useBoardStore } from '@/stores/boardStore';
import { useLoginStore } from '@/stores/loginStore';

const route = useRoute();
const router = useRouter();

const ckeditor = CKEditor.component
const editor = ClassicEditor

// const { boardStore } = useBoardStore();
const { loginStore } = useLoginStore();

const editorDataTitle = ref('')
const editorDataContent = ref('')
const editorConfig = {}

const boardId = route.params.boardId;   // 아이디가 있다면 아이디를 받아오자
let boardType = false;
console.log("boardId :")
console.log(boardId)

const initEditor = async () => {
  if (boardId){
    boardType = true
    let { data } = await http.get('/boards/' + boardId);
    console.log("Load Data : " + data.result)
    editorDataTitle.value = data.dto.boardTitle
    editorDataContent.value = data.dto.boardContent
  }
}

console.log("loginStore : ")
console.log(loginStore)

const insertBoard = async () => {

    // 게시글 등록할 사용자도 세션에서 받아와주기 => userId에 넣어야함
    let formData = {
      boardTitle: editorDataTitle.value,
      boardContent: editorDataContent.value,
      userId: loginStore.userId
    };

    let result = confirm('게시글을 등록하시겠습니까?')
    if (result){
      if (editorDataTitle.value != '' && editorDataContent.value != ''){
        try {
          console.log(formData)

          let { data } = await http.post('/boards', formData)

          console.log(data.result)
          alert('등록이 완료되었습니다.')

          router.push({
              path: '/board'
          })
        } catch (error) {
          console.log(error)
        }
      } else {
        alert('입력을 확인해주세요')
      }
    }
}
    



const updateBoard = async () => {
    let result = confirm('수정을 완료하시겠습니까?')

    let formData = {
      boardId: boardId,
      boardTitle: editorDataTitle.value,
      boardContent: editorDataContent.value
    };
    
    if (result) {
      try {
        let { data } = await http.post('/boards/'+boardId, formData)
        // console.log("updated data : " + data.result)

        // if (data.result == "login"){
        //   // doLogout()
        // } else {
          alert('글이 수정되었습니다')
          router.push({
            name: 'BoardDetail',
            params: boardId
          })  
        // }

      } catch (error) {
        console.log(error)
      }
      
    }

}

initEditor()
</script>



<style scoped>

/* deep selector >>> -> 함수 deep() */
.container:deep(.ck-editor__editable) {
  height: 400px;
}

</style>