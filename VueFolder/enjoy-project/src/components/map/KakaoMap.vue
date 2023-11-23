<template>
  <button class="btn btn-primary" @click="setStartPoint">현재위치</button>
  <div id="map"></div>
</template>

<script setup>
// 사용자의 좌표를 입력하도록 한다?
import { ref, reactive, watch, onMounted } from 'vue'
import { useMapStore } from '@/stores/mapStore'
const { mapStore } = useMapStore()

var map
var polyline = {}
var markers = []
var infowindows = []
var startLatLng = {}
var destLatLng = {}

onMounted(() => {
  // 지도를 한 번만 생성한다. - 이전에 만들어진 지도의 잔상이 남는 현상을 제거
  var mapContainer = document.getElementById('map'), // 지도를 표시할 div
    mapOption = {
      center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
      level: 3 // 지도의 확대 레벨
    }

  // 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
  map = new kakao.maps.Map(mapContainer, mapOption)
  InitMarker()
  // console.log('markers: ', markers)
})

const InitMarker = () => {
  var bounds = new kakao.maps.LatLngBounds()

  // 기존 marker 삭제
  markers.forEach((pin) => {
    pin.setMap(null)
  })

  // 초기화
  markers = []
  infowindows = []

  // marker 생성
  mapStore.pinList.forEach((pin) => {
    var coords = new kakao.maps.LatLng(pin.latitude, pin.longitude)
    var marker = new kakao.maps.Marker({
      map: map,
      position: coords
    })
    markers.push(marker)

    // `<div style="width:200px;text-align:center;padding:6px 0;">${pin.title}</div>`,
    var infowindow = new kakao.maps.InfoWindow({
      content: `
        <div style="width: 200px; text-align: center; padding: 6px 0">목적지: ${pin.title}</div>
      `,
      removable: true
    })
    infowindows.push(infowindow)

    kakao.maps.event.addListener(marker, 'click', function () {
      // 기존 infowindow 닫기
      closeAllInfoWindows()

      destLatLng = {}
      destLatLng = {
        latitude: pin.latitude,
        longitude: pin.longitude
      }
      infowindow.open(map, marker)
      console.log('목적지 설정 ', pin.latitude, pin.longitude)

      getDirection()
    })

    // LatLngBounds 객체를 이용해서 좌표를 적용해 간다.
    bounds.extend(new kakao.maps.LatLng(pin.latitude, pin.longitude))
    // console.log(pin)
  })

  // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
  map.setBounds(bounds)
}

watch(
  () => mapStore.pinList, // getPinList에서 초기화하면서 호출
  () => {
    closeAllInfoWindows()
    InitMarker()
    console.log('markers: ', markers)
    if (Object.keys(polyline).length != 0) {
      polyline.setMap(null)
    }
  }
)

// 현재 위치 표시하기 시작
const displayMarker = (locPosition, message) => {
  var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_red.png'

  var imageSize = new kakao.maps.Size(24, 35)
  var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize)

  // 마커를 생성합니다
  var marker = new kakao.maps.Marker({
    map: map,
    position: locPosition,
    image: markerImage
  })

  var iwContent = message, // 인포윈도우에 표시할 내용
    iwRemoveable = true

  // 인포윈도우를 생성합니다
  var infowindow = new kakao.maps.InfoWindow({
    content: iwContent,
    removable: iwRemoveable
  })

  // 인포윈도우를 마커위에 표시합니다
  infowindow.open(map, marker)

  // 지도 중심좌표를 접속위치로 변경합니다
  map.setCenter(locPosition)
}

const currentLocation = () => {
  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition((position) => {
      var lat = position.coords.latitude // 위도
      var lon = position.coords.longitude // 경도

      startLatLng = {
        latitude: lat,
        longitude: lon
      }
      console.log('현재 위치(startLatLng): ', lat, lon)

      // 현재 위치
      var locPosition = new kakao.maps.LatLng(lat, lon)

      // infowindow content
      var message = `<div style="padding:5px;">현위치</div>`

      displayMarker(locPosition, message)
    })
  } else {
    var locPosition = new kakao.maps.LatLng(37.4812845080678, 126.952713197762)
    var message = '현재 위치를 알 수 없어 기본 위치로 이동합니다.'

    currentLatLon['lat'] = 35.09675
    currentLatLon['lon'] = 128.8543
    console.log('기본 위치: ', currentLatLon['lat'], currentLatLon['lon'])

    displayMarker(locPosition, message)
  }
  return true
}
// 현재 위치 표시하기 끝

// 경로 그리기 시작
const setStartPoint = () => {
  currentLocation()
  closeAllInfoWindows()
  // map.setCenter(locPosition)
}

async function getDirection() {
  if (Object.keys(startLatLng).length == 0) {
    console.log('현재 위치를 알 수 없습니다.')
    return
  }
  // 키와 호출url
  const REST_API_KEY = '364eba0d7152342081052da847c58cf2'
  const url = 'https://apis-navi.kakaomobility.com/v1/directions'

  // 출발지와 목적지의 좌표를 문자열로 변환
  const origin = `${startLatLng.longitude},${startLatLng.latitude}`
  const destination = `${destLatLng.longitude},${destLatLng.latitude}`

  // 요청 헤더를 추가합니다.
  const headers = {
    Authorization: `KakaoAK ${REST_API_KEY}`,
    'Content-Type': 'application/json'
  }

  // 표3의 요청 파라미터에 필수값을 적어줍니다.
  const queryParams = new URLSearchParams({
    origin: origin,
    destination: destination
  })

  const requestUrl = `${url}?${queryParams}` // 파라미터까지 포함된 전체 URL

  try {
    const response = await fetch(requestUrl, {
      method: 'GET',
      headers: headers
    })

    if (!response.ok) {
      throw new Error(`HTTP error! Status: ${response.status}`)
    }

    const data = await response.json()

    const linePath = []
    data.routes[0].sections[0].roads.forEach((router) => {
      router.vertexes.forEach((vertex, index) => {
        // x,y 좌표가 우르르 들어옵니다. 그래서 인덱스가 짝수일 때만 linePath에 넣어봅시다.
        // 저도 실수한 것인데 lat이 y이고 lng이 x입니다.
        if (index % 2 === 0) {
          linePath.push(new kakao.maps.LatLng(router.vertexes[index + 1], router.vertexes[index]))
        }
      })
    })

    // 그려진 polyline 제거
    if (Object.keys(polyline).length != 0) {
      polyline.setMap(null)
    }
    polyline = new kakao.maps.Polyline({
      path: linePath,
      strokeWeight: 5,
      strokeColor: '#1A374D',
      strokeOpacity: 0.7,
      strokeStyle: 'solid'
    })
    polyline.setMap(map)
  } catch (error) {
    console.error('Error:', error)
  }
}

const closeAllInfoWindows = () => {
  for (let idx = 0; idx < infowindows.length; ++idx) {
    infowindows[idx].close()
  }
}
</script>

<style scoped>
#map {
  width: 100%;
  height: 100%;
}
</style>
