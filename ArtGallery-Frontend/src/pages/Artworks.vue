<template>
  <div>
    <Navbar />
    <img src="../assets/artworks-image.jpg" alt="Artworks image" />
    <div class="search-artwork">
      <h1>BROWSE ARTWORKS</h1>
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
        <div></div>
      </div>
      <div>
        <Artwork
          artworkName="Default Artwork"
          artistName="Unknown"
          artworkId="0"
          url="https://i.ibb.co/XzRJG4L/pikachu.png"
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
        Authorization: `Bearer ${localStorage.getItem("token")}`,
      },
    };
    var frontendUrl = "http://" + config.dev.host + ":" + config.dev.port;
    // had to add this to solve cors problem
    var backendUrl =
      "https://cors-anywhere.herokuapp.com/http://" + config.dev.backendHost;
    var AXIOS = axios.create({
      baseURL: backendUrl,
      headers: { "Access-Control-Allow-Origin": frontendUrl },
    });

    const promise = await AXIOS.get(
      "api/customer/artworksForSale",
      configuration
    ).catch((err) => {
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
    Footer,
  },
  data() {
    return {
      artworkArray: {},
    };
  },
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
  width: 100%;
  height: auto;
}
h1 {
  position: absolute;
  text-align: center;
  top: 75%;
  left: 50%;
  /* bring your own prefixes */
  transform: translate(-50%, -50%);
  /* left: 35%; */
  font-family: "Times New Roman", Times, serif;
}
.search-artwork {
  display: inline-block;
}
</style>
