<template>
  <!-- navbar -->
  <div class="site-mobile-menu site-navbar-target">
    <div class="site-mobile-menu-header">
      <div class="site-mobile-menu-close">
        <span class="icofont-close js-menu-toggle"></span>
      </div>
    </div>
    <div class="site-mobile-menu-body"></div>
  </div>

  <nav class="site-nav">
    <div class="container">
      <div class="site-navigation">
        <a href="/main" class="logo m-0">Tour <span class="text-primary">.</span></a>
        <ul class="js-clone-nav d-none d-lg-inline-block text-left site-menu float-right">
          <li class="active">
            <router-link to="/" class="text-primary">Home</router-link>
          </li>
          <li><router-link to="/">게시판</router-link></li>
          <li><a href="#">Search</a></li>
          <li><a href="map/map.html">Map</a></li>

          <!-- User 기능  -->
          <li><a href="login.html">Sign In</a></li>
          <li class="has-children">
            <a href="#">My Page</a>
            <ul class="dropdown">
              <li><a href="mypage.html">My Info</a></li>
              <li><a href="#">Bookmark</a></li>
              <li><a href="/goLogout">Logout</a></li>
            </ul>
          </li>
        </ul>

        <a
          href="#"
          class="burger ml-auto float-right site-menu-toggle js-menu-toggle d-inline-block d-lg-none light"
          data-toggle="collapse"
          data-target="#main-navbar"
        >
          <span></span>
        </a>
      </div>
    </div>
  </nav>
</template>
