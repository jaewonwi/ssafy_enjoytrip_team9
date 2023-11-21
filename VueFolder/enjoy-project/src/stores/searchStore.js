import { ref, reactive, computed } from 'vue'
import { useRouter } from 'vue-router';
import { defineStore } from 'pinia'

import http from "@/common/axios.js";

export const useSearchStore = defineStore('searchStore', () => {
  const router = useRouter()
  const searchStore = reactive({
    // list
    list: [],
    limit: 20,
    offset: 0,

    // pagination
    listRowCount: 20,
    pageLinkCount: 20,
    currentPageIndex: 1,
    totalListItemCount: 0,

    // sido, gugun, contentTypeId
    sidoList: [],
    gugunList: [],
    contentTypeList: [{ code: 12, name: "관광지" },
    { code: 14, name: "문화시설" },
    { code: 25, name: "여행코스" },
    { code: 28, name: "레포츠" },
    { code: 32, name: "숙박" },
    { code: 38, name: "쇼핑" },
    { code: 39, name: "음식점" }],

    // detail
    contentId: 0,
    contentTypeId: 0,
    title: '',
    addr1: '',
    addr2: '',
    firstImage: '',
    latitude: 0,        // 위도
    longitude: 0,       // 경도
    overview: '',       // 상세내용
    sidoCode: 0,
    gugunCode: 0,
    sidoName: '',
    gugunName: '',
    cat1: '',
    cat2: '',
    cat3: '',
  })

  const setSidoList = (list) => searchStore.sidoList = list
  const setGugunList = (list) => searchStore.sidoList = list
  const setAttractionList = (list) => searchStore.list = list
  const setTotalListItemCount = (count) => searchStore.totalListItemCount = count
  const setSearchMovePage = (pageIndex) => {
    searchStore.offset = (pageIndex - 1) * searchStore.listRowCount
    searchStore.currentPageIndex = pageIndex
  }

  const setSearchAttractionDetail = (payload) => {
    searchStore.contentId = payload.contentId;
    searchStore.contentTypeId = payload.contentTypeId;

    searchStore.title = payload.boardTitle;
    searchStore.addr1 = payload.addr1;
    searchStore.addr2 = payload.addr2;
    searchStore.firstImage = payload.firstImage;
    searchStore.latitude = payload.latitude;
    searchStore.longitude = payload.longitude;
    searchStore.overview = payload.overview;    // 상세내용

    searchStore.sidoCode = payload.sidoCode;    // 시도
    searchStore.gugunCode = payload.gugunCode;  // 구군
    searchStore.sidoName = payload.sidoName;
    searchStore.gugunName = payload.gugunName;
    searchStore.cat1 = payload.cat1;            // 대중소 분류
    searchStore.cat2 = payload.cat2;
    searchStore.cat3 = payload.cat3;
  }

  const getSidoList = async () => {
    try {
        console.log("getSidoList")
        let { data } = await http.get('/sidoList')
        console.log(data)
        searchStore.sidoList=data;
    } catch (error) {
        console.log(error);
    }
  }

  const getGugunList = async () => {
    try {
        console.log("getGugunList: sidoCode : "+ searchStore.sidoCode)
        let { data } = await http.get('/gugunList/'+searchStore.sidoCode)
        // console.log(data)
        searchStore.gugunCode = 0;
        searchStore.gugunList = data;
    } catch (error) {
        console.log(error);
    }
  }

  // list
  const attractionList = async () => {
    let params = {
       limit: searchStore.limit,
       offset: searchStore.offset,
       searchWord: searchStore.searchWord,

       sidoCode: searchStore.sidoCode,
       gugunCode: searchStore.gugunCode,
       contentTypeId: searchStore.contentTypeId,
    };

    try {
      let { data } = await http.get("/attractionList", { params }); // params: params shorthand property, let response 도 제거
      console.log("attractionList: data : ");
      console.log(params)
      console.log(data)
       if (data.result == "login") {     // 로그인하지 않았으면 접근 X
          router.push("/login");
       } else {
        setAttractionList(data.list);
        setTotalListItemCount(data.count);

        console.log("총 건수 : "+data.count);
        console.log("pageCount : "+pageCount.value)
       }
    } catch (error) {
       console.error(error);
    }
  }

  // pagination
  const pageCount = computed(() => Math.ceil(searchStore.totalListItemCount / searchStore.listRowCount))
  const startPageIndex = computed(() => {
    if (searchStore.currentPageIndex % searchStore.pageLinkCount == 0) {
      //10, 20...맨마지막
      return (searchStore.currentPageIndex / searchStore.pageLinkCount - 1) * searchStore.pageLinkCount + 1;
    } else {
      return Math.floor(searchStore.currentPageIndex / searchStore.pageLinkCount) * searchStore.pageLinkCount + 1;
    }
  })

  const endPageIndex = pageCount
  // const endPageIndex = computed(() => {
  //   if (searchStore.currentPageIndex % searchStore.pageLinkCount == 0) {
  //     //10, 20...맨마지막
  //     return (searchStore.currentPageIndex / searchStore.pageLinkCount - 1) * searchStore.pageLinkCount + searchStore.pageLinkCount;
  //   } else {
  //     return Math.floor(searchStore.currentPageIndex / searchStore.pageLinkCount) * searchStore.pageLinkCount + searchStore.pageLinkCount;
  //   }
  // })

  const prev = computed(() => searchStore.currentPageIndex <= searchStore.pageLinkCount ? false : true)
  const next = computed(() => 
    Math.floor(pageCount / searchStore.pageLinkCount) * searchStore.pageLinkCount < searchStore.currentPageIndex ? false : true)

  return { 
    searchStore,
    getSidoList, setSidoList, getGugunList, setGugunList, 
    setAttractionList, setSearchMovePage, setTotalListItemCount, setSearchAttractionDetail, attractionList,
    pageCount, startPageIndex, endPageIndex, prev, next 
  }

})