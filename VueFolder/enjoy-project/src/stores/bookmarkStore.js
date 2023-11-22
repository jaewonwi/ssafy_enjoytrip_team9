import http from '@/common/axios.js'
import { ref, reactive } from 'vue'
import { defineStore } from 'pinia'
import { useRouter } from 'vue-router'

export const useBookmarkStore = defineStore('bookmarkStore', () => {

  const bookmarkStore = reactive({
    userId: 0,

    // bookmarkId, userId, contentId
    myBookmarkList: [],
    // 현재 유저의 북마크 리스트: contentId, title, addr1, firstImage, bookmarkId, userId
    viewBookmarkList: []
    
  })

  // 현재 유저의 북마크 리스트 가져오기
const getBookmarkListOfCurUser = async (userId) => {
  // console.log('현재 유저 아이디: ', userId)
  try {
    let { data } = await http.get('/search/bookmarkList/' + userId)
    // console.log('가져온 북마크 정보: ', data)
    bookmarkStore.viewBookmarkList = []
    bookmarkStore.viewBookmarkList = data

    // 관광지 검색 페이지에서 사용할 myBookmarkList 갱신
    bookmarkStore.myBookmarkList = []
    data.forEach((el) => {
      let obj = {
        bookmarkId: el.bookmarkId,
        userId: el.userId,
        contentId: el.contentId,
      }
      bookmarkStore.myBookmarkList.push(obj)
    })
    
    console.log("getBookmarkListOfCurUser(현재 북마크 리스트)")
    console.log(bookmarkStore.myBookmarkList)
    // console.log('현재 북마크 리스트: ', bookmarkStore.viewBookmarkList)
  } catch (error) {
    console.log(error)
  }
}

  const getBookmarkList = async (userId) => {
    console.log("getBookmarkList_userId: ", userId)
    try {
      let { data } = await http.get('/bookmarks/' + userId)
      // console.log(data)
      bookmarkStore.myBookmarkList = []
      bookmarkStore.myBookmarkList = data
      console.log("bookmarkList: ", bookmarkStore.myBookmarkList)
    } catch (error) {
      console.log(error)
    }
  }

  // data.result == 'login' -> setLogout
  const insertBookmark = async (userId, contentId) => {
    // console.log("insertBookmark: ", userId, " " , contentId)
    let obj = {
      userId: userId,
      contentId: contentId
    }
    try {
      let {data} = await http.post('/bookmarks', obj)
      if (data.result == "success") {
        console.log("북마크 추가 성공")
        getBookmarkListOfCurUser(userId)
      }
    } catch (error) {
      console.log(error)
    }
  }

  const deleteBookmark = async (userId, contentId) => {
    // console.log("deleteBookmark: ", userId, " ", contentId)
    try {
      let {data} = await http.delete('/bookmarks/' + userId + '/' + contentId)
      if (data.result == "success") {
        console.log("북마크 삭제 성공")
        getBookmarkListOfCurUser(userId)
      }
    } catch (error) {
      console.log(error)
    }
  }


  return { bookmarkStore, insertBookmark, deleteBookmark, getBookmarkList, getBookmarkListOfCurUser }
})