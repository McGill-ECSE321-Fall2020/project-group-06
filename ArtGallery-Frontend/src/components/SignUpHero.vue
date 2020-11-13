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
              v-model="artistChecked"
              id="artist"
            />
            <label for="artist">Artist</label>
            <input
              type="radio"
              value="Customer"
              v-model="customerChecked"
              id="customer"
            />
            <label for="customer">Customer</label>
          </div>
          <br />
          <p>{{ message }}</p>
          <p class="error">{{ errorMessage }}</p>
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
  updated() {
    // check radio buttons
    if (this.artistChecked === "" && this.customerChecked === "") {
      this.message = "Creating a default user account";
      this.accountType = "USER";
    } else if (
      this.artistChecked === "Artist" &&
      this.customerChecked === "Customer"
    ) {
      this.message = "Warning: Cannot select two accounts types!";
    } else if (this.artistChecked === "Artist") {
      this.message = "Creating an artist account";
      this.accountType = "ARTIST";
    } else {
      this.message = "Creating a customer account";
      this.accountType = "CUSTOMER";
    }

    // check for confirm passwords
    if (this.password !== this.passwordConfirmation) {
      this.errorMessage = "Warning: Passwords are not identical!";
    } else {
      this.errorMessage = "";
    }
  },
  name: "SignUpHero",
  data() {
    return {
      accountType: "",
      artistChecked: "",
      customerChecked: "",
      email: "",
      username: "",
      password: "",
      passwordConfirmation: "",
      message: "Creating a default user account",
      errorMessage: ""
    };
  },
  methods: {
    async handleSubmit() {
      var frontendUrl = "http://" + config.dev.host + ":" + config.dev.port;
      var backendUrl =
        "https://cors-anywhere.herokuapp.com/http://" + config.dev.backendHost;
      var AXIOS = axios.create({
        baseURL: backendUrl,
        headers: { "Access-Control-Allow-Origin": frontendUrl }
      });

      if (
        this.username === "" ||
        this.password === "" ||
        this.passwordConfirmation === ""
      ) {
        this.errorMessage = "Fields can't be empty!";
        return;
      }

      // check accountType and create account according to corresponding accountYpe
      if (this.accountType === "USER" || this.accountType === "") {
        const userResponse = await AXIOS.post("api/user/createUser", {
          username: this.username,
          password: this.password
        }).catch(err => {
          console.log(err);
          this.errorMessage = "Something went wrong";
        });
        console.log(userResponse);
        window.location.href = "#/login";
        window.scrollTo(0, 0);
      } else if (this.accountType === "ARTIST") {
        const artistResponse = await AXIOS.post("api/artist/createArtist", {
          username: this.username,
          password: this.password
        }).catch(err => {
          console.log(err);
          this.errorMessage = "Something went wrong";
        });
        console.log(artistResponse);
        window.location.href = "#/login";
        window.scrollTo(0, 0);
      } else {
        console.log("Creating customer");
        const customerResponse = await AXIOS.post(
          "api/customer/createCustomer",
          {
            username: this.username,
            password: this.password
          }
        ).catch(err => {
          console.log(err);
          this.errorMessage = "Something went wrong";
        });
        console.log(customerResponse);
        window.location.href = "#/login";
        window.scrollTo(0, 0);
      }
    }
  }
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
.error {
  color: palevioletred;
}
</style>
