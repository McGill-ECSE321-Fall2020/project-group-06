<template>
  <div>
    <Navbar />
    <div class="filter-area">
      <br />
      <br />
    </div>
    <div id="container">
      <Artwork />
      <div class="description">
         Description:
      </div>
      <div>
        <button type="button" class="button">BUY</button>  
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
  flex-wrap: nowrap;
}

#container > div {
  margin: 20px;
  padding: 0px;
  width: 400px;
  transition: transform 0.5s; /* Animation */
}
#container > div:hover {
  transform: scale(1,1);
}
.button {
  border: none;
  color: white;
  font-size: 20px;
  text-align: center;
  background-color: #32CD32;
}
</style>
