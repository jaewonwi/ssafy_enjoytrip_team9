import http from '@/common/axios.js'
import { defineStore } from 'pinia'
import { ref, reactive } from 'vue'
import { useRouter } from 'vue-router'


export const  useMapStore = defineStore('mapStore', () => {

  // sidoCode, sidoName
  // sidoList.push({sidoCode: '', sidoName: ''})
  const sidoList = reactive([])

  // gugunCode, gugunName, sidoCode
  const gugunList = reactive([])

  const selectLoc = reactive({
    sidoCode: 1,
    // sidoName: '서울',
  })


  const getSidoList = async () => {
    try {
      let {data} = await http.get('/attractions')
      console.log(data)
      data.forEach((sido) => {
        //console.log(sido.sidoCode, sido.sidoName)
        sidoList.push({sidoCode: sido.sidoCode, sidoName: sido.sidoName});
      })
    } catch (error) {
      console.log(error)
    }
  }

  const getGugunList = async (sidoCode) => {
    console.log("getGugunList: " + sidoCode)
    setSidoList(sidoCode)
    try {
      let { data } = await http.get('/attractions/gugunList/' + sidoCode)
      // console.log(data)
      gugunList.push({
        gugunCode: data.gugunCode,
        gugunName: data.gugunName,
        sidoCode: data.sidoCode
      })
    } catch (error) {
      console.log(error)
    }
  }

  const getPinList = (sidoCode, gugunCode) => {

  }

  const setSidoList = (sidoCode) => {
    selectLoc.sidoCode = sidoCode,
    selectLoc.sidoName = ''
  }

  return { sidoList, getSidoList, gugunList, getGugunList, selectLoc }
})
