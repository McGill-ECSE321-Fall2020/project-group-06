<template>
  <div>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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
          <button type="button" class="button" v-if="!isBuy" v-on:click="isBuy=true">BUY</button> 
          <button class="favorite" v-on:click.prevent="handleFavorite">
            <i v-if="isInFavorites" title="Remove from Favorites" class="fa fa-heart" id="heartFav"></i>
            <i v-if="!isInFavorites" title="Add to Favorites" class="fa fa-heart" id="heartNotFav"></i>
          </button>
        </div>
        <div class="available" v-if="isAvailable">
          available in store
        </div>
        <div class="notavailable" v-else color=red >
          not available in store
        </div>
        <div>
          {{ typeArtwork }}
        </div>
        <div class="description">
          Description: {{ description }} <p>Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Vestibulum tortor quam, feugiat vitae, ultricies eget, tempor sit amet, ante. Donec eu libero sit amet quam egestas semper. Aenean ultricies mi vitae est. Mauris placerat eleifend leo.</p>
        </div> 
      </div>
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

    console.log("FAVORITES");
    console.log(this.customer.artwork);
    var favorites = this.customer.artwork;
    for (const artwork of favorites) {
      if(artwork.id == this.artwork.id) {
        console.log("Is in favorite list");
        this.isInFavorites=true;
        break;
      }
    }

  },

  methods: {
    async handleFavorite() {
      console.log("Handle Favorites");
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

      var errorOccured = false;
      if(!this.isInFavorites) {
        console.log("Adding to favorites");
        const promise = await AXIOS.post(
          "api/customer/addArtwork/" + this.customer.id + "/" + this.artwork.id,
          {},
          configuration
        ).catch((err) => {
          console.log(err);
          errorOccured = true;
        });
      }
      else {
        console.log("Removing from favorites");
        const promise = await AXIOS.post(
          "api/customer/removeArtwork/" + this.customer.id + "/" + this.artwork.id,
          {},
          configuration
        ).catch((err) => {
          console.log(err);
          errorOccured = true;
        });
      }

      if(!errorOccured) {
        this.isInFavorites = !this.isInFavorites;
      }
    }
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
      status: "success", 
      isInFavorites: false
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
  color: darkseagreen;
  font-size: 24px;
  text-align: center;
  border-color: darkseagreen;
  margin-left: 30%;
  border-radius: 8px;
}
.button:hover {
  transform: scale(1.2);
  transition: transform 0.25s;
}

.favorite {
  margin-left: 10%;
  font-size: 100%;
  border-radius: 50%;
  text-align:center;
  background-color: white;
  border:none;
  outline: none;
}

.favorite:hover {
  transform: scale(1.2);
  transition: transform 0.25s;
}

.favorite:hover > #heartNotFav {
  color:red;
}

.favorite:hover > #heartFav {
  color:lightpink;
}

#heartFav #heartNotFave {
  font-size:24px;
}

#heartFav {
  color:red;
}
#heartNotFav {
  color:lightpink;
}

.minicontainer {
  display: grid;
}

.price {
  margin-top: 10%;
  text-align: left;
  font-size: 30px;
}
.description {
  margin-top: 10%;
}
.available {
  font-size: 10px;
  margin-bottom: 10%;
  color: darkseagreen;
}
.notavailable {
  font-size: 10px;
  margin-bottom: 10%;
  color: orangered;
}
</style>
