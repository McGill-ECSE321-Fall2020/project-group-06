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
            <div class="error" v-if="status == 'fail'">
              <p> Error: You must be logged in with a customer account to buy! </p>
            </div>
            <div v-else> 
              <ConfirmTransaction 
                v-bind:artwork="artwork" 
                v-bind:customer="customer"
              />
            </div>
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
          Description: {{ description }} <p>Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Vestibulum tortor quam, feugiat vitae, ultricies eget, tempor sit amet, ante. Donec eu libero sit amet quam egestas semper. Aenean ultricies mi vitae est. Mauris placerat eleifend leo.</p>
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

//NEED TO LOG IN WITH CUSTOMER ACCOUNT
    var username = localStorage.getItem("username");
		console.log(username);
		var promise2 = await AXIOS.get(
		  "api/customer/getCustomer/" + username,
		  configuration
		).catch((err) => {
      this.status = "fail";
		  console.log(err);
    });
		console.log("after");	
    try{
      this.customer = promise2.data;
    }
    catch(err){
      console.log(err);
    }
    console.log(this.customer);	
    console.log(this.status);
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
      customer: "",
      status: "success"
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

.error {
  font-size: 15px;
  color: red;
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
  display: grid;
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
