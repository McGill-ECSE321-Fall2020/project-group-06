<template>
  <div>
    <img src="../assets/login-hero.jpg" alt="Login Hero Image" />
    <form @submit.prevent="handleSubmit">
      <div class="container">
        <div class="centered-text">
          <b>Login to your account</b>
          <br />
          <br />
          <div class="form-group">
            <input
              type="text"
              class="form-control"
              v-model="username"
              placeholder="username"
            />
          </div>
          <div class="form-group">
            <input
              type="password"
              class="form-control"
              v-model="password"
              placeholder="password"
            />
            <a href="#">forgot password?</a>
            <p>{{ status }}</p>
          </div>
          <button>Login</button>
        </div>
      </div>
    </form>
  </div>
</template>

<script>
import axios from "axios";
var config = require("../../config");

export default {
  name: "LoginHero",
  data() {
    return {
      username: "",
      password: "",
      status: "",
    };
  },
  methods: {
    async handleSubmit() {
      var loggedIn = true;
      var frontendUrl = "http://" + config.dev.host + ":" + config.dev.port;
      // had to add this to solve cors problem
      var backendUrl =
        "https://cors-anywhere.herokuapp.com/http://" + config.dev.backendHost;
      var AXIOS = axios.create({
        baseURL: backendUrl,
        headers: { "Access-Control-Allow-Origin": frontendUrl },
      });
      const response = await AXIOS.post("api/cognito/authenticate", {
        userName: this.username,
        password: this.password,
      }).catch((err) => {
        this.status = "Something went wrong";
        loggedIn = false;
      });
      if (loggedIn) {
        this.status = "You are logged in!";
        localStorage.setItem("token", response.data);
        localStorage.setItem("username", this.username);
      }
      console.log(response);
      localStorage.setItem("token", response.data);
      console.log(localStorage.getItem("token"));
    },
  },
};
</script>

<style scoped>
.test {
  background-color: red;
}
* {
  box-sizing: border-box;
}
img {
  max-width: 100%;
  height: auto;
}
input {
  background-color: white;
}
.centered-text {
  position: absolute;
  top: 29%;
  font-size: large;
  left: 63%;
  transform: translate(-50%, -50%);
}
a {
  font-size: small;
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
</style>
