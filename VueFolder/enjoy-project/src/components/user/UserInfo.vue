<script setup></script>

<template>
  <div class="untree_co-section">
    <div class="container justify-content-center">
      <div class="row">
        <div class="col">
          <div class="p-5">
            <div class="text-center">
              <h1 class="h2 text-gray-900 mb-4 section-title text-center">내 정보</h1>
            </div>
            <div class="form-group">
              <input
                type="text"
                class="form-control form-control-user"
                id="inputName"
                value="이름"
              />
            </div>
            <div class="form-group">
              <h4 class="form-control form-control-user">DB에서 유저 아이디 가져오기</h4>
            </div>
            <div class="form-group">
              <div class="row">
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
            <div class="text-center">
              <button class="btn btn-primary" id="btnEdit" type="button">수정하기</button>
              <button class="btn btn-secondary" id="btnDel" type="button">탈퇴하기</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
