<template>
  <!-- Footer -->
  <footer class="page-footer font-small pt-4" style="background-color: #DDD8CC">
    <!-- Footer Links -->
    <div class="container-fluid text-center text-md-left">
      <!-- Grid row -->
      <div class="row">
        <!-- Grid column -->
        <div class="col-md-6 mt-md-0 mt-3">
          <!-- Content -->
          <h5 class="text-uppercase">ArtGallerySystem</h5>
          <p>Thanks for visiting our website!</p>
        </div>
        <!-- Grid column -->

        <hr class="clearfix w-100 d-md-none pb-3" />

        <!-- Grid column -->
        <div class="col-md-3 mb-md-0 mb-3">
          <!-- Links -->
          <h5 class="text-uppercase">Links</h5>

          <ul class="list-unstyled">
            <li>
              <a href="#/" class="link">Home</a>
            </li>
            <li>
              <a href="#/aboutUs" class="link">About us</a>
            </li>
            <li>
              <a href="#/artworks" class="link">Artwork</a>
            </li>
            <li>
              <a href="#/login" class="link">Login</a>
            </li>
          </ul>
        </div>
        <!-- Grid column -->

        <!-- Grid column -->
        <div class="col-md-3 mb-md-0 mb-3">
          <!-- Links -->
          <h5 class="text-uppercase">Links</h5>

          <ul class="list-unstyled">
            <li>
              <a href="https://www.mcgill.ca" class="link">Mcgill Website</a>
            </li>
            <li>
              <a href="#/profile" class="link">Profile</a>
            </li>
            <li>
              <a href="#/commission" class="link">Commission</a>
            </li>
            <li>
              <a
                href="https://www.youtube.com/watch?v=-lEfBU3ikN4&ab_channel=10HoursMovies"
                class="link"
                >Business</a
              >
            </li>
          </ul>
        </div>
        <!-- Grid column -->
      </div>
      <!-- Grid row -->
    </div>
    <!-- Footer Links -->

    <!-- Copyright -->
    <div class="footer-copyright text-center py-3">
      © 2020 Copyright:
      <a href="https://mdbootstrap.com/"> MDBootstrap.com</a>
    </div>
    <!-- Copyright -->
  </footer>
  <!-- Footer -->
</template>

<script>
export default {
  name: "Footer",
};
</script>

<style scoped>
a {
  text-decoration: none;
  color: black;
}
a:hover {
  color: grey;
}
</style>
