<template>
  <div>
    <Navbar />
    <div class="search-artwork">
      <img src="../assets/artworks-image.jpg" alt="Artworks image" />
      <div class="centered">BROWSE ARTWORKS</div>
    </div>
    <div class="filter-area">
      <br />
      <br />
    </div>
    <div id="container">
      <div v-for="artwork in artworkArray" :key="artwork.id">
        <Artwork
          v-bind:artworkName="artwork.name"
          v-bind:artistName="artwork.artist.username"
          v-bind:artworkId="artwork.id"
          v-bind:url="artwork.url"
        />
      </div>
    </div>
    <Footer />
  </div>
</template>

<script>
import Navbar from "../components/Navbar";
import Artwork from "../components/Artwork";
import Footer from "../components/Footer";
import axios from "axios";
var config = require("../../config");

export default {
  async beforeCreate() {
    console.log("Before Create");
    const configuration = {
      headers: {
        Authorization: `Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJWaW5jZW50IFZhbiBHb2doIiwiZXhwIjoxNjA1MTU4NzYyLCJpYXQiOjE2MDUxMjI3NjJ9.LIPqM1I_Qb_ZXN-YAZPnUqM3-c81JjLxvIZWE17mkOw`
      }
    };
    var frontendUrl = "http://" + config.dev.host + ":" + config.dev.port;
    // had to add this to solve cors problem
    var backendUrl =
      "https://cors-anywhere.herokuapp.com/http://" + config.dev.backendHost;
    var AXIOS = axios.create({
      baseURL: backendUrl,
      headers: { "Access-Control-Allow-Origin": frontendUrl }
    });

    const promise = await AXIOS.get(
      "api/customer/artworksForSale",
      configuration
    ).catch(err => {
      console.log(err);
    });
    // populate the array
    this.artworkArray = promise.data;
    console.log(this.artworkArray);
  },
  name: "Artworks",
  components: {
    Navbar,
    Artwork,
    Footer
  },
  data() {
    return {
      artworkArray: {}
    };
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
.centered {
  color: white;
  position: absolute;
  top: 25%;
  font-size: 40px;
  left: 50%;
  transform: translate(-50%, -50%);
}
</style>
