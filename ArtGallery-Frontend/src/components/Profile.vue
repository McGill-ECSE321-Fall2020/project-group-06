<template>
  <section id="profile" class="body">
    <mdb-row>
      <mdb-col md="3">
        <mdb-card cascade narrow class="text-center pb-3">
          <mdb-view>
            <img
              src="../assets/default_avatar.png"
              alt="Project"
              class="img-fluid"
            />
            <mdb-mask overlay="white-slight" waves />
          </mdb-view>
          <mdb-card-body class="text-center">
            <mdb-card-title class="font-bold mb-2">
              <strong>{{ firstName }} {{ lastName }}</strong>
            </mdb-card-title>
            <h5 class="indigo-text" v-if="isArtist">
              <strong>Artist</strong>
            </h5>
            <h5 class="indigo-text" v-if="!isArtist">
              <strong>Customer</strong>
            </h5>
            <h6 class="text-justify">
              <strong>About:</strong>
            </h6>
            <p class="text-justify">
              {{ description }}
            </p>
            <h6 class="text-justify">
              <strong>Phone Number:</strong>
            </h6>
            <p class="text-justify">{{ phoneNumber }}</p>
            <h6 class="text-justify">
              <strong>Email:</strong>
            </h6>
            <p class="text-justify">{{ email }}</p>
            <div class="text-right">
              <mdb-btn outline="primary" rounded size="sm" @click="editProfile"
                >Edit Profile</mdb-btn
              >
              <mdb-btn outline="primary" rounded size="sm">More...</mdb-btn>
            </div>
          </mdb-card-body>
        </mdb-card>
      </mdb-col>
      <mdb-col md="5">
        <section class="text-center pb-3">
          <mdb-row
            class="d-flex justify-content-center"
            v-for="artw in artwork"
            :key="artw.id"
          >
            <mdb-col lg="6" xl="5" class="mb-3" v-if="isArtist">
              <Artwork
                v-bind:artworkName="artw.name"
                v-bind:artworkId="artw.id"
                v-bind:url="artw.url"
                v-bind:artistName="lastName"
              />
            </mdb-col>
            <mdb-col lg="6" xl="5" class="mb-3" v-if="!isArtist">
              <Artwork
                v-bind:artworkName="artw.name"
                v-bind:artworkId="artw.id"
                v-bind:url="artw.url"
                v-bind:artistName="artw.artist.LastName"
              />
            </mdb-col>
          </mdb-row>
          <mdb-col class="mb-3">
            <mdb-btn outline="primary" rounded size="sm" @click="addArtwork"
              >Add Artwork</mdb-btn
            >
          </mdb-col>
        </section>
      </mdb-col>
      <mdb-col md="4">
        <section class="text-center pb-3">
          <mdb-row
            class="d-flex justify-content-center"
            v-for="transac in transaction"
            :key="transac.id"
          >
            <mdb-col lg="6" xl="5" class="mb-3">
              <Transaction v-bind:transactionId="transac.id" />
            </mdb-col>
          </mdb-row>
        </section>
      </mdb-col>
    </mdb-row>
  </section>
</template>

<script>
import axios from "axios";
var config = require("../../config");
import {
  mdbRow,
  mdbCol,
  mdbCard,
  mdbCardBody,
  mdbView,
  mdbMask,
  mdbCardTitle,
  mdbCardText,
  mdbCardFooter,
  mdbIcon,
  mdbBtn,
  mdbPagination,
  mdbPageNav,
  mdbPageItem,
} from "mdbvue";
import Artwork from "../components/Artwork";
import Transaction from "../components/Transaction";
export default {
  async beforeCreate() {
    console.log("Before create profile");
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
    const response = await AXIOS.get(
      "api/user/getUser/" + username,
      configuration
    ).catch((err) => {
      console.log(err);
    });
    console.log(response.data);
    this.transaction = response.data.transaction;
    this.artwork = response.data.artwork;
    this.bankAccountNumber = this.bankAccountNumber;
    this.id = response.data.id;
    this.password = response.data.password;
    this.username = response.data.username;
    this.firstName = response.data.firstName;
    this.lastName = response.data.lastName;
    this.email = response.data.email;
    this.description = response.data.description;
    this.phoneNumber = response.data.phoneNumber;
    this.creditCardNumber = response.data.creditCardNumber;
    this.isArtist = false;
    if (!this.creditCardNumber == 0) {
      this.isArtist = true;
    }
    console.log(this.isArtist + "isArtist");
  },
  methods: {
    editProfile() {
      window.location.href = "#/editProfile";
      window.scrollTo(0, 0);
    },
    addArtwork() {
      // if(this.isArtist){
      window.location.href = "#/addArtwork";
      window.scrollTo(0, 0);
      // }
      // else{
      //   window.location.href = "#/artworks";
      //   window.scrollTo(0, 0);
      // }
    },
  },
  data() {
    return {
      transaction: [],
      artwork: [],
      bankAccountNumber: "",
      id: "",
      password: "",
      username: "",
      firstName: "",
      lastName: "",
      email: "",
      description: "",
      phoneNumber: "",
      creditCardNumber: "",
      isArtist: "",
    };
  },
  name: "Profile",
  props: ["type"],
  components: {
    mdbRow,
    mdbCol,
    mdbCard,
    mdbCardBody,
    mdbView,
    mdbMask,
    mdbCardTitle,
    mdbCardText,
    mdbCardFooter,
    mdbIcon,
    mdbBtn,
    mdbPagination,
    mdbPageNav,
    mdbPageItem,
    Artwork,
    Transaction,
  },
};
</script>

<style>
.body {
  padding-top: 5rem;
}
</style>
