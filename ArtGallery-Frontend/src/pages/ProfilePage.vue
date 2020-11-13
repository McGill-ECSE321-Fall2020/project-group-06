<template>
  <div>
    <Navbar />
    <Profile />
    <Footer />
  </div>
</template>

<script>
import axios from "axios";
var config = require("../../config");
import Navbar from "../components/Navbar";
import Footer from "../components/Footer";
import Profile from "../components/Profile";
export default {
  async beforeCreate() {
    console.log("Before Profile");
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
  },
  name: "ProfilePage",
  components: {
    Navbar,
    Profile,
    Footer,
  },
};
</script>

<style>
</style>