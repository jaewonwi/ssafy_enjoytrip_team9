import { reactive, computed } from 'vue'
import { useRouter } from 'vue-router';
import { defineStore } from 'pinia'

import http from "@/common/axios.js";

export const useBoardStore = defineStore('boardStore', () => {
  const router = useRouter()
  const boardStore = reactive({
    // list
    list: [],
    limit: 10,
    offset: 0,
    searchWord: "",

    // pagination
    listRowCount: 10,
    pageLinkCount: 10,
    currentPageIndex: 1,
    totalListItemCount: 0,

    // detail, update, delete
    boardId: 0,
    title: "",
    content: "",
    userNm: "",
    regDateTime: "",
    // regDate: "",
    // regTime: "",
    userProfile: "",
    readCount: 0,
    like: 0,
    sameUser: false,
  })

  const setBoardList = (list) => boardStore.list = list
  const setTotalListItemCount = (count) => boardStore.totalListItemCount = count
  const setBoardMovePage = (pageIndex) => {
    boardStore.offset = (pageIndex - 1) * boardStore.listRowCount
    boardStore.currentPageIndex = pageIndex
  }
  const setBoardDetail = (payload) => {
    boardStore.boardId = payload.boardId;
    boardStore.title = payload.boardTitle;
    boardStore.content = payload.boardContent;
    boardStore.userNm = payload.userNm;
    boardStore.regDateTime = payload.regDateTime;
    // boardStore.regDate = payload.boardRegDate.date;
    // console.log(boardStore.boardRegDate)
    // boardStore.regTime = payload.boardRegDate.time;
    boardStore.userProfile = payload.userProfile;
    boardStore.readCount = payload.boardReadCount;
    boardStore.like = payload.boardLike;
    boardStore.sameUser = payload.sameUser;
  }

  // list
  const boardList = async () => {
    let params = {
       limit: boardStore.limit,
       offset: boardStore.offset,
       searchWord: boardStore.searchWord,
    };

    try {
      let { data } = await http.get("/boards", { params }); // params: params shorthand property, let response 도 제거
      console.log("boardStore: data : ");
      console.log(data);
       if (data.result == "login") {     // 로그인하지 않았으면 접근 X
          router.push("/login");
       } else {
        setBoardList(data.list);
        setTotalListItemCount(data.count);
        console.log("총 건수 : "+data.count);
        console.log("pageCount : "+pageCount.value)
       }
    } catch (error) {
       console.error(error);
    }
  }

  // delete
  const deleteBoard = async () => {
    let result = confirm("정말로 삭제하시겠습니까?")
    console.log(result)
    if (result) {
        try {
            let { data } = await http.delete('/boards/'+boardId)
            console.log(data)
            router.push('/board')
        } catch (error) {
            console.log(error)
        }
    }
  }



  // pagination
  const pageCount = computed(() => Math.ceil(boardStore.totalListItemCount / boardStore.listRowCount))
  const startPageIndex = computed(() => {
    if (boardStore.currentPageIndex % boardStore.pageLinkCount == 0) {
      //10, 20...맨마지막
      return (boardStore.currentPageIndex / boardStore.pageLinkCount - 1) * boardStore.pageLinkCount + 1;
    } else {
      return Math.floor(boardStore.currentPageIndex / boardStore.pageLinkCount) * boardStore.pageLinkCount + 1;
    }
  })

  const endPageIndex = computed(() => {
    let tempEndPageIndex = 0;
    if (boardStore.currentPageIndex % boardStore.pageLinkCount == 0) {
      //10, 20...맨마지막
      tempEndPageIndex = ((boardStore.currentPageIndex / boardStore.pageLinkCount) - 1) * boardStore.pageLinkCount + boardStore.pageLinkCount;
    } else {
      tempEndPageIndex = Math.floor(boardStore.currentPageIndex / boardStore.pageLinkCount) * boardStore.pageLinkCount + boardStore.pageLinkCount;
    }
    // endPageIndex 가 전체 pageCount(페이지 전체 수) 보다 크면 페이지 전체 수로 보정 
    if( tempEndPageIndex > pageCount.value ) tempEndPageIndex = pageCount.value
    return tempEndPageIndex;
  })
  
  
  const prev = computed(() => boardStore.currentPageIndex <= boardStore.pageLinkCount ? false : true)
  const next = computed(() => endPageIndex.value == pageCount.value ? false : true) // 위에서 더 큰 값은 보정했으므로 같은 지만 비교

  return { 
    boardStore, 
    setBoardList, setBoardMovePage, setTotalListItemCount, setBoardMovePage, setBoardDetail, boardList, deleteBoard,
    pageCount, startPageIndex, endPageIndex, prev, next 
  }

})