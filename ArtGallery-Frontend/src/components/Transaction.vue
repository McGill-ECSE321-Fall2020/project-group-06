<template>
  <div>
    <div class="transaction-card">
      <div class="container">
        <h4><b>Transaction</b></h4>
        <p>Date: {{ dateOfTransaction }}</p>
        <p>Artwork: {{ artworkName }}</p>
        <p>Artist: {{ artistUsername }}</p>
        <p>Price: {{ price }}$</p>
        <p>Commission: {{ price * 0.15 }}$</p>
        <p>Delivery: {{ delivery }} </p>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
var config = require("../../config");
export default {
  name: "Transaction",
  props: {
    transactionId: {
      type: Number,
      default: 10,
    },
  },
  async created() {
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
    const response = await AXIOS.get(
      "api/transaction/getTransaction/" + this.transactionId,
      configuration
    ).catch((err) => {
      console.log(err);
    });
    this.artworkName = response.data.artwork.name;
    this.artistFirstName = response.data.artist.firstName;
    this.artistLastName = response.data.artist.lastName;
    this.price = response.data.artwork.price;
    this.dateOfTransaction = response.data.dateOfTransaction;
    this.artistUsername = response.data.artist.username;
    var del = response.data.deliveryType;
    if(del === "Delivered") {
      this.delivery = del;
    }
    else {
      this.delivery = "Picked Up";
    }
  },
  data() {
    return {
      artworkName: "",
      artistFirstName: "",
      artistLastName: "",
      price: "",
      dateOfTransaction: "",
      artistUsername: "",
      delivery: ""
    };
  },
};
</script>

<style scoped>
.transaction-card {
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
  transition: 0.3s;
  width: 100%;
  background-color: #ddd8cc;
}
​ .transaction-card:hover {
  box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2);
}
​ .container {
  padding: 2px 16px;
}
</style>
