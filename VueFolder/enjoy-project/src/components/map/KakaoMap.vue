<template>
  <div id="map"></div>
</template>

<script setup>
import { ref, watch, onMounted, onUpdated } from 'vue'
import { useMapStore } from '@/stores/mapStore'
const { mapStore } = useMapStore()

// const markers = ref([])

onMounted(() => {
  InitMarker()
})

// 이거때매 확대/축소할때 버벅거릴 수 있다
onUpdated(() => {
  InitMarker()
})

const InitMarker = () => {
  var mapContainer = document.getElementById('map'), // 지도를 표시할 div
    mapOption = {
      center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
      level: 3 // 지도의 확대 레벨
    }

  // 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
  var map = new kakao.maps.Map(mapContainer, mapOption)
  var bounds = new kakao.maps.LatLngBounds()

  // marker 생성
  console.log('================')
  mapStore.pinList.forEach((pin) => {
    var coords = new kakao.maps.LatLng(pin.latitude, pin.longitude)
    var marker = new kakao.maps.Marker({
      map: map,
      position: coords
    })

    // `<div style="width:200px;text-align:center;padding:6px 0;">${pin.title}</div>`,
    var infowindow = new kakao.maps.InfoWindow({
      content: `<div style="width:200px;text-align:center;padding:6px 0;">${pin.title}</div>`,
      removable: true
    })

    kakao.maps.event.addListener(marker, 'click', function () {
      infowindow.open(map, marker)
    })

    // LatLngBounds 객체를 이용해서 좌표를 적용해 간다.
    bounds.extend(new kakao.maps.LatLng(pin.latitude, pin.longitude))
    console.log(pin)
  })

  // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
  map.setBounds(bounds)
}

watch(
  () => mapStore.pinList, // getPinList에서 초기화하면서 호출
  () => {
    InitMarker()
  }
)
</script>

<style>
#map {
  width: 100%;
  height: 100%;
}
</style>
