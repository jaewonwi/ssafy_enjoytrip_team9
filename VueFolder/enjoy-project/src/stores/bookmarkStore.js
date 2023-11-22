import http from '@/common/axios.js'
import { ref, reactive } from 'vue'
import { defineStore } from 'pinia'
import { useRouter } from 'vue-router'

export const useBookmarkStore = defineStore('bookmarkStore', () => {

  const bookmarkStore = reactive({
    userId: 0,

    // bookmarkId, userId, contentId
    bookmarkList: [],

  })

  // data.result == 'login' -> setLogout
  const insertBookmark = async (userId, contentId) => {
    console.log("insertBookmark: ", userId, " " , contentId)
    let obj = {
      userId: userId,
      contentId: contentId
    }
    try {
      let {data} = await http.post('/bookmarks', obj)
      if (data.result == "success") {
        console.log("북마크 추가 성공")
      }
    } catch (error) {
      console.log(error)
    }
  }

  const deleteBookmark = async (userId, contentId) => {
    console.log("deleteBookmark: ", userId, " ", contentId)
    try {
      let {data} = await http.delete('/bookmarks/' + userId + '/' + contentId)
      if (data.result == "success") {
        console.log("북마크 삭제 성공")
      }
    } catch (error) {
      console.log(error)
    }
  }


  return { bookmarkStore, insertBookmark, deleteBookmark }
})