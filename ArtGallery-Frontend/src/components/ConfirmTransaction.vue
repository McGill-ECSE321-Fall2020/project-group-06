<template>
  <div class="confirm-card">
	<div class="container">
		Artwork: {{ artwork.name }} <br>
		Artist: {{ artwork.artist.username }} <br>
		Price: {{ artwork.price }} <br>
		etc <br>
		customerid: {{ customer.id }}
	</div>
	<button class="confirm">CONFIRM</button>
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
    };
  },
  methods: {
	/*async beforeCreate() {
		console.log("Before Create in Confirm");
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
		
		var username = localStorage.getItem("username");
		console.log(username);
		const promise = await AXIOS.get(
		"api/customer/getCustomer/" + username,
		configuration
		).catch((err) => {
		console.log(err);
		});
		console.log("after");
		console.log(this.customer);		
		this.customer = promise.data;
  	},
    async handleConfirm() {
      
    },*/
  },
};
</script>

<style scoped>
.confirm-card {
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
  width: 100%;
  background-color: #ddd8cc;
}

.container {
  padding: 2px 16px;
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
  color: red;
  font-size: 24px;
  text-align: center;
  border-color: red;
  margin-top: 20%;
  margin-left: 10%;
  border-radius: 8px;
}

.confirm:hover {
  color: white;
  background-color: red;
  transform: scale(1.2);
  transition: transform 0.25s;
}
</style>
