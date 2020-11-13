<template>
  <!-- bg-light to have a background 
   add style="background-color: #AB9778;" for custom background color-->
  <nav
    class="navbar navbar-expand-lg navbar-light fixed-top"
    style="background-color: #DDD8CC; margin-bot: 70px"
  >
    <a class="navbar-brand" href="#">Art Gallery</a>
    <button
      class="navbar-toggler"
      type="button"
      data-toggle="collapse"
      data-target="#navbarSupportedContent"
      aria-controls="navbarSupportedContent"
      aria-expanded="false"
      aria-label="Toggle navigation"
    >
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav mr-auto">
        <li class="nav-item active">
          <a class="nav-link" href="http://127.0.0.1:8087/#/"
            >Home <span class="sr-only">(current)</span></a
          >
        </li>
        <li class="nav-item">
          <a class="nav-link" href="http://127.0.0.1:8087/#/artworks"
            >Artworks</a
          >
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Contacts</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="http://127.0.0.1:8087/#/aboutUs">About us</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="http://127.0.0.1:8087/#/login">Login</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" v-bind:href="'http://127.0.0.1:8087/#/artist/'"
            >Profile</a
          >
        </li>
      </ul>
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit">
        <a class="nav-link" href="http://127.0.0.1:8087/#/signup">SignUp</a>
      </button>
    </div>
  </nav>
</template>

<script>
export default {
  name: "Navbar",
};
</script>

<style scoped>
</style>
