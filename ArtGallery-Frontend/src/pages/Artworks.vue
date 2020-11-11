<template>
  <div>
    <Navbar />
    <div class="search-artwork">
      <img src="../assets/artworks-image.jpg" alt="Artworks image" />
    </div>
    <div class="filter-area">
      <br />
      <br />
    </div>
    <div id="container">
      <div v-for="index in 12" :key="index">
        <Artwork artworkName="Mona Lisa" artistName="DaVinki" artworkId="1" />
      </div>
    </div>
    <Footer />
  </div>
</template>

<script>
import Navbar from "../components/Navbar";
import Artwork from "../components/Artwork";
import Footer from "../components/Footer";
export default {
  name: "Artworks",
  components: {
    Navbar,
    Artwork,
    Footer
  }
};
</script>

<style scoped>
#container {
  display: flex;
  flex-wrap: wrap;
}

#container > div {
  margin: 20px;
  padding: 20px;
  width: 420px;
  transition: transform 0.5s; /* Animation */
}
#container > div:hover {
  transform: scale(1.2);
}
img {
  max-width: 100%;
  height: auto;
}
</style>
