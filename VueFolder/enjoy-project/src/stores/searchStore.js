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
    { code: 15, name: "축제공연행사" },
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
    zipcode: '',
    tel: '',
    firstImage: '',
    firstImage2: '',
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

  const mainSearchStore = reactive({
    tour: [],
    culture: [],
    festival: [],
    leisure: [],
  })

  const setSidoList = (list) => searchStore.sidoList = list
  const setGugunList = (list) => searchStore.sidoList = list
  const setAttractionList = (list) => searchStore.list = list
  const setTotalListItemCount = (count) => searchStore.totalListItemCount = count
  const setSearchMovePage = (pageIndex) => {
    searchStore.offset = (pageIndex - 1) * searchStore.listRowCount
    searchStore.currentPageIndex = pageIndex
  }

  const setSearchAttractionDetail = async (payload) => {
    searchStore.contentId = payload.contentId;
    searchStore.contentTypeId = payload.contentTypeId;

    searchStore.title = payload.title;
    searchStore.addr1 = payload.addr1;
    searchStore.addr2 = payload.addr2;
    searchStore.zipcode = payload.zipcode;
    searchStore.tel = payload.tel;
    searchStore.firstImage = payload.firstImage;
    searchStore.firstImage2 = payload.firstImage2;
    searchStore.latitude = payload.latitude;
    searchStore.longitude = payload.longitude;
    searchStore.overview = payload.overview;    // 상세내용

    searchStore.sidoCode = payload.sidoCode;    // 시도
    searchStore.gugunCode = payload.gugunCode;  // 구군
    searchStore.sidoName = payload.sidoName;
    searchStore.gugunName = payload.gugunName;
    
    // searchStore.cat1 = payload.cat1;            // 대중소 분류
    // searchStore.cat2 = payload.cat2;
    // searchStore.cat3 = payload.cat3;
  }

  const getSidoList = async () => {
    try {
        let { data } = await http.get('/search/sidoList')
        searchStore.sidoList = data;
    } catch (error) {
        console.log(error);
    }
  }

  const getGugunList = async () => {
    try {
        let { data } = await http.get('/search/gugunList/'+searchStore.sidoCode)
        searchStore.gugunCode = 0;
        searchStore.gugunList = data;
    } catch (error) {
        console.log(error);
    }
  }

  const getTypeList = async () => {
    try {
      searchStore.contentTypeId = 0;
    } catch (error) {
        console.log(error);
    }
  }
  // list
  const attractionList = async () => {
    // console.log(searchStore.sidoCode);
    // console.log(searchStore.gugunCode);
    // console.log(searchStore.contentTypeId);


    let params = {
       limit: searchStore.limit,
       offset: searchStore.offset,
       searchWord: searchStore.searchWord,

       sidoCode: searchStore.sidoCode,
       gugunCode: searchStore.gugunCode,
       contentTypeId: searchStore.contentTypeId,
    };

    try {
      let { data } = await http.get("/search/attractionList", { params }); // params: params shorthand property, let response 도 제거
      //console.log("attractionList: data : ");
      //console.log(params)
      //console.log(data)
       if (data.result == "login") {     // 로그인하지 않았으면 접근 X
          router.push("/login");
       } else {
        //  list = [{
        //    isBookmark: true false,
        //  }]
        setAttractionList(data.list);
        setTotalListItemCount(data.count);

        console.log(data.list)
        //console.log("총 건수 : "+data.count);
        //console.log("pageCount : "+pageCount.value)
       }
    } catch (error) {
       console.error(error);
    }


  }

  // recommend list - main
  const setRecommendTourList = async () => {
    let { data } = await http.get("/search/recommendList/6/"+12); // params: params shorthand property, let response 도 제거
    mainSearchStore.tour = data.list
    // console.log(data.list)
  }
  const setRecommendCultureList = async () => {
    let { data } = await http.get("/search/recommendList/6/"+14); // params: params shorthand property, let response 도 제거
    mainSearchStore.culture = data.list
    // console.log(data.list)
  }
  const setRecommendFestivalList = async () => {
    let { data } = await http.get("/search/recommendList/6/"+15); // params: params shorthand property, let response 도 제거
    mainSearchStore.festival = data.list
    // console.log(data.list)
  }
  const setRecommendLeisureList = async () => {
    let { data } = await http.get("/search/recommendList/6/"+28); // params: params shorthand property, let response 도 제거
    mainSearchStore.leisure = data.list
    // console.log(data.list)
  }

  const attractionMainList = async () => {
    try {
      setRecommendTourList();
      setRecommendCultureList();
      setRecommendFestivalList();
      setRecommendLeisureList();
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

  const endPageIndex = computed(() => {
    let tempEndPageIndex = 0;
    if (searchStore.currentPageIndex % searchStore.pageLinkCount == 0) {
      //10, 20...맨마지막
      tempEndPageIndex = ((searchStore.currentPageIndex / searchStore.pageLinkCount) - 1) * searchStore.pageLinkCount + searchStore.pageLinkCount;
    } else {
      tempEndPageIndex = Math.floor(searchStore.currentPageIndex / searchStore.pageLinkCount) * searchStore.pageLinkCount + searchStore.pageLinkCount;
    }
    // endPageIndex 가 전체 pageCount(페이지 전체 수) 보다 크면 페이지 전체 수로 보정 
    if( tempEndPageIndex > pageCount.value ) tempEndPageIndex = pageCount.value
    return tempEndPageIndex;
  })
  
  const prev = computed(() => searchStore.currentPageIndex <= searchStore.pageLinkCount ? false : true)
  const next = computed(() => endPageIndex.value == pageCount.value ? false : true) // 위에서 더 큰 값은 보정했으므로 같은 지만 비교

  return { 
    searchStore, mainSearchStore,
    getSidoList, setSidoList, getGugunList, setGugunList, getTypeList, setAttractionList, attractionMainList,
    setSearchMovePage, setTotalListItemCount, setSearchAttractionDetail, attractionList,
    pageCount, startPageIndex, endPageIndex, prev, next, pageCount 
  }

})