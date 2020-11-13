<template>
  <div>
    <img src="../assets/artworks-image.jpg" alt="SignUp image" />
    <form @submit.prevent="handleSubmit">
      <div class="container">
        <div class="centered-text">
          <h1 class="SignUpTitle"><b>SignUp</b></h1>
          <br />
          <br />
          <div class="RadioButtons">
            <input
              type="radio"
              value="Artist"
              v-model="SignUpform.AccountType"
              id="artist"
            />
            <label for="artist">Artist</label>
            <input
              type="radio"
              value="Customer"
              v-model="SignUpform.AccountType"
              id="customer"
            />
            <label for="customer">Customer</label>
          </div>
          <br />
          <br />
          <div class="form-group">
            <input
              type="email"
              class="form-control"
              v-model="email"
              placeholder="email"
            />
          </div>
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
          </div>
          <div class="form-group">
            <input
              type="password"
              class="form-control"
              v-model="passwordConfirmation"
              placeholder="confirm password"
            />
          </div>
          <!-- <a href="#">forgot password?</a> -->
          <button>SignUp</button>
        </div>
      </div>
    </form>
  </div>
</template>

<script>
import axios from "axios";
var config = require("../../config");

export default {
  name: "SignUpHero",
  data() {
    return {
      SignUpform: {
        AccountType: null,
        email: "",
        username: "",
        password: "",
        passwordConfirmation: "",
      },
    };
  },
  methods: {
    async handleSubmit() {
      var frontendUrl = "http://" + config.dev.host + ":" + config.dev.port;
      var backendUrl = "https://" + config.dev.backendHost;
      var AXIOS = axios.create({
        baseURL: backendUrl,
        headers: { "Access-Control-Allow-Origin": frontendUrl },
      });
      const response = await AXIOS.post("api/user/createUser", {
        email: this.email,
        username: this.username,
        password: this.password,
      }).catch((err) => {
        console.log(err);
      });
      console.log(response);
      window.location.href = "#/profile";
      window.scrollTo(0, 0);
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
  width: 100%;
  height: auto;
}
input {
  background-color: white;
}
.SignUpTitle {
  left: 15%;
  position: absolute;
}
.centered-text {
  position: absolute;
  top: 50%;
  font-size: large;
  left: 50%;
  transform: translate(-50%, -50%);
}
.RadioButtons {
  left: 10%;
  position: absolute;
  direction: columns;
}
a {
  font-size: small;
}

button {
  left: 25%;
  background-color: #ddd8cc;
  border: none;
  color: black;
  position: absolute;
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
