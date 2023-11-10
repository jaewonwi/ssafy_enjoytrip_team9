<template>
  <!-- 회원 가입 form -->
  <div class="hero hero-inner">
    <div class="container">
      <div class="row align-items-center">
        <div class="col-lg-6 mx-auto text-center">
          <div class="intro-wrap">
            <h1 class="mb-0">회원가입</h1>
          </div>
        </div>
      </div>
    </div>
  </div>

  <div class="untree_co-section">
    <div class="container justify-content-center">
      <div class="row">
        <div class="col">
          <div class="p-5">
            <form class="user">
              <div class="form-group">
                <div class="mb-sm-0">
                  <input
                    type="text"
                    class="form-control form-control-user"
                    id="userNm"
                    name="userNm"
                    placeholder="Name"
                  />
                </div>
              </div>
              <div class="form-group">
                <input
                  type="email"
                  class="form-control form-control-user"
                  id="userEmail"
                  name="userEmail"
                  placeholder="Email Address for ID"
                />
              </div>
              <div class="form-group row">
                <div class="col-sm-6 mb-3 mb-sm-0">
                  <input
                    type="password"
                    class="form-control form-control-user"
                    id="userPwd"
                    name="userPwd"
                    placeholder="Password"
                  />
                </div>
                <div class="col-sm-6">
                  <input
                    type="password"
                    class="form-control form-control-user"
                    id="userRepeatPwd"
                    name="userRepeatPwd"
                    placeholder="Repeat Password"
                  />
                </div>
              </div>
              <div class="text-center">
                <button class="btn btn-primary" id="btnRegist" type="button">회원가입</button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup></script>
