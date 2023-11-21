import http from '@/common/axios.js'
import { defineStore } from 'pinia'
import { reactive } from 'vue'


export const  useMapStore = defineStore('mapStore', () => {

  // 
  // sidoList.push({sidoCode: '', sidoName: ''})
  const mapStore = reactive({
    // 시도 구군의 기본 선택값
    selectSido: -1,
    selectGugun: -1,

    
    sidoList: [{sidoCode: -1, sidoName: '시도 선택'}],       // sidoCode, sidoName
    gugunList: [{gugunCode: -1, gugunName: '구군 선택', sidoCode: -1}],      // gugunCode, gugunName, sidoCode
    // selectLocList: [],  // contentId, sidoCode, gugunCode, gugunName, latitude, longitude

    // pin
    pinList: [],        // contentId, title, addr1, addr2, firstImage, latitude, longitude

    // 상세정보
    selectLoc: {
      
    }
  })


  const getSidoList = async () => {
    try {
      let {data} = await http.get('/attractions')
      console.log(data)
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
    console.log("getGugunList: " + sidoCode)
    try {
      let { data } = await http.get('/attractions/gugunList/' + sidoCode)
      console.log(data)
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

        // pin을 추가하기 위한 selectLocList에 저장
        // mapStore.selectLocList.push({
        //   contentId: locationData.contentId,
        //   sidoCode: locationData.sidoCode,
        //   gugunCode: locationData.gugunCode,
        //   gugunName: locationData.gugunName,
        //   latitude: locationData.latitude,
        //   longitude: locationData.longitude,
        // })
      })

      console.log("gugunList: ", mapStore.gugunList)
    } catch (error) {
      console.log(error)
    }
  }

  const getPinList = async (sidoCode, gugunCode) => {
    console.log("select sido, gugun Code: ", sidoCode, " | ", gugunCode)
    try {
      let { data } = await http.get('/attractions/' + sidoCode + "/" + gugunCode)
      // console.log(data)
      
      // contentId, title, addr1, addr2, firstImage, latitude, longitude
      data.forEach((pin) => {
        mapStore.pinList.push({
          contentId: pin.contentId,
          title: pin.title,
          addr1: pin.addr1,
          addr2: pin.addr2,
          contenfirstImagetId: pin.firstImage,
          latitude: pin.latitude,
          longitude: pin.longitude,
        })  
      })

      console.log("pinList: ", mapStore.pinList[0])
      
    } catch (error) {
      console.log(error)
    }
  }

  return { mapStore, getSidoList, getGugunList, getPinList }
})
