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
          <button type="button" class="button">BUY</button> 
        </div>
        <div class="availability">
          available in store
        </div>
        <div>
          TYPE OF ARTWORK
        </div>
        <div class="description">
          Description:
        </div> 
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
  color: #32CD32;
  font-size: 24px;
  text-align: center;
  border-color: #32CD32;
  margin-left: 10%;
  border-radius: 8px;
}
.button:hover {
  transform: scale(1.2);
  transition: transform 0.25s;
}

.minicontainer {
  display: grid
}

.price {
  text-align: left;
  font-size: 30px;
}
.description {
  margin-top: 10%;
}

.availability {
  font-size: 10px;
  margin-bottom: 10%;
}

</style>
