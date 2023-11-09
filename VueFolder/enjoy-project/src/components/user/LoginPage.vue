<template>
  <div class="hero hero-inner">
    <div class="container">
      <div class="row align-items-center">
        <div class="col-lg-6 mx-auto text-center">
          <div class="intro-wrap">
            <h1 class="mb-0">Login</h1>
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
            <div class="text-center">
              <h1 class="h2 text-gray-900 mb-4">Login</h1>
            </div>
            <form name="login-form" class="login-form">
              <div class="form-group">
                <input
                  type="email"
                  class="form-control form-control-user"
                  id="userId"
                  aria-describedby="emailHelp"
                  placeholder="Enter Email Address..."
                  value="ssafy@email.com"
                />
              </div>
              <div class="form-group">
                <input
                  type="password"
                  class="form-control form-control-user"
                  id="userPassword"
                  placeholder="Password"
                  value="1234"
                />
              </div>
              <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                <button class="btn btn-primary" id="btnLogin" type="button">로그인</button>
                <button class="btn btn-primary" id="btnRegist" type="button">회원가입</button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
