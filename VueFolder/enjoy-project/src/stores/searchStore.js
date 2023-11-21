import { reactive, computed } from 'vue'
import { useRouter } from 'vue-router';
import { defineStore } from 'pinia'

import http from "@/common/axios.js";

export const useSearchStore = defineStore('searchStore', () => {
  const router = useRouter()
  const searchStore = reactive({
    // list
    list: [],
    limit: 10,
    offset: 0,

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

  const endPageIndex = pageCount
  // const endPageIndex = computed(() => {
  //   if (boardStore.currentPageIndex % boardStore.pageLinkCount == 0) {
  //     //10, 20...맨마지막
  //     return (boardStore.currentPageIndex / boardStore.pageLinkCount - 1) * boardStore.pageLinkCount + boardStore.pageLinkCount;
  //   } else {
  //     return Math.floor(boardStore.currentPageIndex / boardStore.pageLinkCount) * boardStore.pageLinkCount + boardStore.pageLinkCount;
  //   }
  // })

  const prev = computed(() => boardStore.currentPageIndex <= boardStore.pageLinkCount ? false : true)
  const next = computed(() => 
    Math.floor(pageCount / boardStore.pageLinkCount) * boardStore.pageLinkCount < boardStore.currentPageIndex ? false : true)

  return { 
    boardStore, 
    setBoardList, setBoardMovePage, setTotalListItemCount, setBoardMovePage, setBoardDetail, boardList,
    pageCount, startPageIndex, endPageIndex, prev, next 
  }

})