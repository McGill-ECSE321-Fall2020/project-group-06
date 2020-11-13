<template>
  <div>
    <div class="transaction-card">
      <div class="container">
        <h4><b>Transaction</b></h4>
        <p>Date: {{ transactionId }}</p>
        <p>Artwork: {{}}</p>
        <p>Artist: {{}}</p>
        <p>Price: {{}}</p>
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
  async beforeCreate() {
    console.log(this.transactionId);
    console.log("Before create Transaction");

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
    console.log("before axios");
    const response = await AXIOS.get(
      "api/transaction/getTransaction/" + this.transactionId,
      configuration
    ).catch((err) => {
      console.log(err);
    });
    console.log("after axios");
    console.log(response.data);
  },
  data() {
    return {};
  },
};
</script>

<style>
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
