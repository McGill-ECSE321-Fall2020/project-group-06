<template>
  <div>
    <Navbar />
    <div class="filter-area">
      <br />
      <br />
    </div>
    <div id="container">
      <Artwork 
        v-bind:artistName="artwork.artist.username" 
        v-bind:artworkName="artwork.name" 
        v-bind:artworkId="artwork.id"
        v-bind:url="artwork.url"
      />
      <div id="minicontainer">
        <div class="price">
          {{ price }} $
          <button type="button" class="button" v-on:click="isBuy=true">BUY</button> 
          <div v-if="isBuy"> 
            <ConfirmTransaction 
              v-bind:artwork="artwork" 
              v-bind:customer="customer"
            />
          </div>
        </div>
        <div class="availability" v-if="isAvailable" >
          available in store
        </div>
        <div class="availability" v-else >
          not available in store
        </div>
        <div>
          {{ typeArtwork }}
        </div>
        <div class="description">
          Description: {{ description }}
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
import ConfirmTransaction from "../components/ConfirmTransaction";

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
    
    var name = this.$route.fullPath.split("/")[3].replace(/%20/g, " ");
    console.log(name);
    const promise = await AXIOS.get(
      "api/artwork/getArtwork/" + name,
      configuration
    ).catch((err) => {
      console.log(err);
    });
    // populate the array
    this.artwork = promise.data;
    console.log(this.artwork);
    this.price = this.artwork.price;
    this.isAvailable = this.artwork.isInStore;
    this.description = this.artwork.description;
    this.typeArtwork = this.artwork.typeOfArtwork;

    var username = localStorage.getItem("username");
		console.log(username);
		var promise2 = await AXIOS.get(
		  "api/customer/getCustomer/" + username,
		  configuration
		).catch((err) => {
		  console.log(err);
		});
		console.log("after");	
    this.customer = promise2.data;
    console.log(this.customer);	
  },
  name: "BuyArtwork",
  components: {
    Navbar,
    Artwork,
    Footer, 
    ConfirmTransaction
  },
  data() {
    return {
      artwork: "",
      price: "",
      isAvailable: "",
      description: "",
      typeArtwork: "",
      isBuy: false, 
      customer: ""
    };
  },
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
  margin-top: 20%;
  margin-left: 30%;
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
