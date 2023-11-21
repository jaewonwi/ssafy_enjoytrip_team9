import http from '@/common/axios.js'
import { defineStore } from 'pinia'
import { reactive } from 'vue'


export const  useMapStore = defineStore('mapStore', () => {

  const mapStore = reactive({
    // 시도 구군의 기본 선택값
    selectSido: -1,
    selectGugun: -1,

    
    sidoList: [{sidoCode: -1, sidoName: '시도 선택'}],       // sidoCode, sidoName
    gugunList: [{gugunCode: -1, gugunName: '구군 선택', sidoCode: -1}],      // gugunCode, gugunName, sidoCode

    // pin
    // contentId, title, addr1, addr2, firstImage, latitude, longitude
    pinList: [{ title: 'tesst', latitude: 33.450701, longitude: 126.570667 }],        

    // 상세정보
    selectLoc: {
      
    }
  })


  const getSidoList = async () => {
    try {
      let {data} = await http.get('/attractions')
      // console.log(data)
      // 기존에 있던 list 삭제
      mapStore.sidoList = []
      mapStore.sidoList.push({ sidoCode: -1, sidoName: '시도 선택' })
      mapStore.selectLocList = -1
      mapStore.selectGugun = -1

      data.forEach((sido) => {
        //console.log(sido.sidoCode, sido.sidoName)
        mapStore.sidoList.push({
          sidoCode: sido.sidoCode,
          sidoName: sido.sidoName
        });
      })
      // 저장 완료
      // console.log("sidoList: ", mapStore.sidoList)
    } catch (error) {
      console.log(error)
    }
  }

  const getGugunList = async (sidoCode) => {
    // console.log("getGugunList: " + sidoCode)
    try {
      let { data } = await http.get('/attractions/gugunList/' + sidoCode)
      // console.log(data)
      // 기존에 있던 list 삭제
      mapStore.gugunList = []
      mapStore.selectLocList = []
      mapStore.gugunList.push({gugunCode: -1, gugunName: '구군 선택', sidoCode: -1})
      mapStore.selectGugun = -1

      data.forEach((gugun) => {
        // gugunList에 저장
        mapStore.gugunList.push({
          gugunCode: gugun.gugunCode,
          gugunName: gugun.gugunName,
          sidoCode: gugun.sidoCode,
        })
      })

      // console.log("gugunList: ", mapStore.gugunList)
    } catch (error) {
      console.log(error)
    }
  }

  const getPinList = async (sidoCode, gugunCode) => {
    // console.log("select sido, gugun Code: ", sidoCode, " | ", gugunCode)
    try {
      let { data } = await http.get('/attractions/' + sidoCode + "/" + gugunCode)
      // console.log(data)

      // pinList 초기화
      mapStore.pinList = data
    } catch (error) {
      console.log(error)
    }
  }

  return { mapStore, getSidoList, getGugunList, getPinList }
})
