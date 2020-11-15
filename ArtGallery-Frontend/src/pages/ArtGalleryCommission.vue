<template>
  <div>
    <Navbar />
    <img src="../assets/commission.jpg" alt="" />
    <h3>Online Art Gallery Transaction History</h3>
    <div id="container">
      <div v-for="transaction in transactionArray" :key="transaction.id">
        <Transaction v-bind:transactionId="transaction.id" />
      </div>
    </div>
    <Footer />
  </div>
</template>

<script>
import axios from "axios";
var config = require("../../config");
import Navbar from "../components/Navbar";
import Footer from "../components/Footer";
import Transaction from "../components/Transaction";

export default {
  async beforeCreate() {
    console.log("Before create commission");
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

    const transactionPromise = await AXIOS.get(
      "/api/artgallery/allTransactions",
      configuration
    ).catch((err) => {
      console.log(err);
    });
    console.log(transactionPromise.data);
    this.transactionArray = transactionPromise.data;
  },
  name: "ArtGalleryCommission",
  components: {
    Navbar,
    Footer,
    Transaction,
  },
  data() {
    return {
      transactionArray: [],
      isAdmin: false,
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
</style>