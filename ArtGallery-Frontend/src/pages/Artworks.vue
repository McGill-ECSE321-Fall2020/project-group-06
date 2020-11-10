<template>
  <div>
    <Navbar />
    <div class="search-artwork">
      <img src="../assets/spooky-af.jpg" alt="spooky image" />
    </div>
    <div id="container">
      <div><Artwork /></div>
      <div><Artwork /></div>
      <div><Artwork /></div>
      <div><Artwork /></div>
      <div><Artwork /></div>
      <div><Artwork /></div>
      <div><Artwork /></div>
      <div><Artwork /></div>
      <div><Artwork /></div>
      <div><Artwork /></div>
      <div><Artwork /></div>
      <div><Artwork /></div>
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
  background-color: lightgrey;
  margin: 20px;
  padding: 20px;
  width: 420px;
}
img {
  max-width: 100%;
  height: auto;
}
</style>
