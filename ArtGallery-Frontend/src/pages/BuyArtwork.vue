<template>
  <div>
    <Navbar />
    <div class="filter-area">
      <br />
      <br />
    </div>
    <div id="container">
      <Artwork picture="hero-image.jpg" />
      <div id="minicontainer">
        <div class="price">
          PRICE$
        </div>
        <div>
          TYPE OF ARTWORK
        </div>
        <div class="description">
          Description:
        </div>
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
  name: "BuyArtwork",
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
.button:hover {
  transform: scale(1.2);
  transition: transform 0.5s;
}

.minicontainer {
  display: grid
}

#minicontainer > div {
  margin-top: 10%;
}
.price {
  text-align: center;
  font-size: 30px;
}

</style>
