<template>
  <div>
    <Navbar />
    <div class="filter-area">
      <br />
      <br />
    </div>
    <div id="container">
      <Artwork artistName="DaVin" artworkName="Mona Lisa2" url="hero-image.jpg" />
      <div id="minicontainer">
        <div class="price">
          {{ price }}$
          <button type="button" class="button">BUY</button> 
        </div>
        <div class="availability" v-if="isAvailable">
          available in store
        </div>
        <div class="availability" v-else>
          not available in store
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
import axios from "axios";
import Navbar from "../components/Navbar";
import Artwork from "../components/Artwork";
import Footer from "../components/Footer";
var config = require("../../config");

export default {
  name: "BuyArtwork",
  components: {
    Navbar,
    Artwork,
    Footer
  },
  data() {
    return {
      artist: "",
      artwork: "",
      type: "",
      description: "",
      price: "80",
      isAvailable: false,
      status: ""
    };
  },
  created: function() {
    const config = {
      headers: { Authorization: "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjaGFnZ3kiLCJleHAiOjE2MDUxNTg2MDIsImlhdCI6MTYwNTEyMjYwMn0.eY3S6JYm5I2vWcdwr3XW9WM_9B5aq7pQQWMj3oeeopo"}
    }
    var loggedIn = true;
    var frontendUrl = "http://" + config.dev.host + ":" + config.dev.port;
    // had to add this to solve cors problem
    var backendUrl =
      "https://cors-anywhere.herokuapp.com/http://" + config.dev.backendHost;
    var AXIOS = axios.create({
      baseURL: backendUrl,
      headers: { "Access-Control-Allow-Origin": frontendUrl }
    });
    this.artworkName = "Mona Lisa2"
    AXIOS.get("api/artwork/getartwork/" + this.artworkName, config ).then(response => {
      console.log(response)
      // TODO
    }).catch(err => {
      this.status = "Something went wrong";
    });
  },
  methods: {
     createTransaction: function() {
       // TODO
     }
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
