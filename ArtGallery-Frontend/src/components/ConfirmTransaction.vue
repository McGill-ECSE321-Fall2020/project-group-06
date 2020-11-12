<template>
  <div class="confirm-card">
    <form @submit.prevent="handleSubmit">
      <h1 class="title"> Transaction Preview </h1>
      <div class="container">
        Artwork: {{ artwork.name }} <br>
        Artist: {{ artwork.artist.username }} <br>
        Price: {{ artwork.price }}$ <br>
        <label for="mean">Mean of delivery:</label>
        <select name="Mean of Delivery" id="mean" v-model="meanOfDelivery" required>
          <option value="pickup">Pick Up</option>
          <option value="deliver">Deliver</option>
        </select> <br>
        Buyer: {{ customer.username }} <br>
        Credit Card: {{ customer.creditCardNumber }}
        <br>
        <br>
        <div class="message">
          Do you want to continue with your purchase?
        </div>
      </div>
      <input type="submit" value="CONFIRM" class="confirm">
    </form>
  </div>
</template>

<script>
import axios from "axios";
var config = require("../../config");

export default {
  name: "ConfirmTransaction",
  props: ["artwork", "customer"],
  data() {
	return {
    meanOfDelivery: null,
  };
  },
  methods: {
	  async handleSubmit() {
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
        headers: { 
          "Access-Control-Allow-Origin": frontendUrl
        },
      });
    /*
      const response = await AXIOS.post(
            "api/customer/buyArtwork/" + this.customer.id + "/" + this.artwork.artist.id + "/" + this.artwork.id + "/" + this.artwork.artGallery.id, {},
            configuration ).catch((err) => {
          console.log(err);
      });

      console.log(response);
      */
      var DelType;
      if (this.meanOfDelivery == "Pick Up") {
        DelType="PickedUp";
      }
      else {
        DelType="Delivered";
      }
      console.log(DelType);
  
      function compare(a,b) {
        const id1 = a.id;
        const id2 = b.id;

        let comparison = 0;
        if(id1 > id2){
          comparison = 1;
        } else if(id1 < id2) {
          comparison = -1;
        }
        return comparison;
      } 

      var transactions = this.customer.transaction.sort(compare);
      console.log(transactions);

      var lastTransaction = transactions[transactions.length - 1];
      console.log(lastTransaction);
   
      const response2 = await AXIOS.post(
            "api/customer/setMeanOfDelivery/" + lastTransaction.id, 
            "PickedUp",
            configuration ).catch((err) => {
          console.log(err);
      });
    },
  },
};
</script>

<style scoped>
.confirm-card {
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
  width: 100%;
  margin-top: 10%;
  background-color: #ddd8cc;
}
.title {
  font-size: 100%;
  text-align: center;
  text-decoration: underline;
}
.container {
  padding: 2px 16px;
  font-size: 16px;
  font-weight: bold;
}
.message {
  font-size: 16px;
  font-weight: bold;
  color:orangered;
}
button {
  background-color: #ddd8cc;
  border: none;
  color: black;
  padding: 15px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  opacity: 0.5;
  border-radius: 12px;
}
button:hover {
  opacity: 1;
}
.confirm {
  color: orangered;
  font-size: 24px;
  text-align: center;
  border-color: orangered;
  margin-top: 10%;
  margin-left: 5%;
  margin-bottom: 5%;
  border-radius: 8px;
}

.confirm:hover {
  color: white;
  background-color: orangered;
  transform: scale(1.2);
  transition: transform 0.25s;
}
</style>
