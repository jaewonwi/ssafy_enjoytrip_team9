<template>
  <div class="hero hero-inner">
    <div class="container">
      <div class="row align-items-center">
        <div class="col-lg-6 mx-auto text-center">
          <div class="intro-wrap">
            <h1 class="mb-0">마이페이지</h1>
          </div>
        </div>
      </div>
    </div>
  </div>

  <div class="untree_co-section">
    <div class="container justify-content-center">
      <div class="container row-6 user">
        <div class="text-left">
          <h1 class="h2 text-gray-900 mb-4 mx-auto text-center">마이페이지</h1>
        </div>
        <div class="row form-group">
          <div class="col-sm-3">
            <h4>이름 :</h4>
          </div>
          <div class="col-sm">
            <input type="text" class="form-control form-control-user" id="inputName" value="이름" />
          </div>
        </div>
        <div class="row form-group">
          <div class="col-sm-3">
            <h4>아이디 (E-mail) :</h4>
          </div>
          <!-- <input type="email" class="form-control form-control-user col-sm"
					id="userEmail" placeholder="abcde123@gmail.com" /> -->
          <div class="col-sm">
            <h4>DB에서 유저 아이디 가져오기</h4>
          </div>
        </div>
        <div class="row form-group">
          <div class="col-sm-3">
            <h4>비밀번호 :</h4>
          </div>

          <div class="col-sm row">
            <div class="col-sm">
              <input
                type="password"
                class="form-control form-control-user"
                id="inputPassword"
                placeholder="Password"
              />
            </div>
            <div class="col-sm">
              <input
                type="password"
                class="form-control form-control-user"
                id="repeatPassword"
                placeholder="Repeat Password"
              />
            </div>
          </div>
        </div>

        <div class="row">
          <button class="col btn btn-primary btn-user btn-block m-5" id="btnEdit" type="button">
            수정하기
          </button>
          <button class="col btn btn-secondary btn-user btn-block m-5" id="btnDel" type="button">
            탈퇴하기
          </button>
        </div>
      </div>
    </div>
  </div>
</template>
