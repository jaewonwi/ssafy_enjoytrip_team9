<template>
  <div class="hero">
    <div class="container">
      <div class="row align-items-center">
        <div class="col-lg-7">
          <div class="intro-wrap">
            <h1 class="mb-4"><span class="d-block">여행을 계획해볼까요?</span> <span class="typed-words"></span>는 어떠세요?</h1>

            <div class="row">
              <div class="col-12">
                <form class="form">
                  <div class="row mb-2">
                    <div class="col-sm-12 col-md-6 mb-3 mb-lg-0 col-lg-4">
                      <select name id="area1List" class="form-control custom-select">
                        <option value="">시도</option>
                      </select>
                    </div>
                    <div class="col-sm-12 col-md-6 mb-3 mb-lg-0 col-lg-4">
                      <select name id="area2List" class="form-control custom-select">
                        <option value="">구군</option>
                      </select>
                    </div>
                    <div class="row align-items-center">
                      <div class="col-sm-12 col-md-6 mb-3 mb-lg-0 col-lg-4">
                        <button id="btnSearch" type="button" class="btn btn-primary">Search</button>
                      </div>
                    </div>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
        <div class="col-lg-5">
          <div class="slides">
            <img src="/images/hero-slider-1.jpg" alt="Image" class="img-fluid active" />
            <img src="/images/hero-slider-2.jpg" alt="Image" class="img-fluid" />
            <img src="/images/hero-slider-3.jpg" alt="Image" class="img-fluid" />
            <img src="/images/hero-slider-4.jpg" alt="Image" class="img-fluid" />
            <img src="/images/hero-slider-5.jpg" alt="Image" class="img-fluid" />
            <img src="/images/hero-slider-5.jpg" alt="Image" class="img-fluid" />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
$(function () {
  var slides = $('.slides'),
    images = slides.find('img')

  images.each(function (i) {
    $(this).attr('data-id', i + 1)
  })

  var typed = new Typed('.typed-words', {
    strings: [' 제주', ' 대구', ' 경주', ' 강원도', ' 대전', ' 부산'],
    typeSpeed: 80,
    backSpeed: 80,
    backDelay: 4000,
    startDelay: 1000,
    loop: true,
    showCursor: true,
    preStringTyped: (arrayPos, self) => {
      arrayPos++
      //console.log(arrayPos);
      $('.slides img').removeClass('active')
      $('.slides img[data-id="' + arrayPos + '"]').addClass('active')
    }
  })
})

window.onload = function () {
  document.querySelector('#area1List').onchange = selectGugun
  document.querySelector('#btnSearch').onclick = searchOnMap

  async function searchOnMap() {
    // 사용자가 입력한 파라미터 준비
    // 백엔드 url 요청 - fetch post
    // 결과 : json
    // 성공 / 실패

    let sido = document.querySelector('#area1List').value
    let gugun = document.querySelector('#area2List').value

    let urlParams = new URLSearchParams({
      job: 'searchMap',
      sido: sido,
      gugun: gugun
    })

    let fetchOptions = {
      method: 'POST',
      body: urlParams
    }
    let response = await fetch('/searchMap', fetchOptions)
    let data = await response.json()
    console.log(data)

    if (data.result == 'success') {
      window.location.href = '/main'
    } else if (data.result == 'fail') {
      alert('아이디 또는 비밀번호가 틀렸습니다.')
    }
  }

  async function selectGugun() {
    // 선택한 시도 정보를 받는다.
    let selectSidoCode = document.querySelector('#area1List').value
    console.log(selectSidoCode)

    let response = await fetch(`/map?action=gugun&select=` + selectSidoCode)
    const gugunList = await response.json()
    console.log(gugunList)

    // sidoName에 속한 구군 정보를 보여준다.
    let area2List = document.querySelector('#area2List')

    // 초기화 후에 세팅
    area2List.innerHTML = ''
    gugunList.forEach((gugun) => {
      let option = document.createElement('option')
      option.value = gugun.gugunCode
      option.text = gugun.gugunName
      area2List.appendChild(option)
    })
  }
}
</script>
