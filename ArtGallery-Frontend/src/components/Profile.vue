<template>
  <section id="profile">
    <mdb-row>
      <mdb-col md="3">
        <mdb-card cascade narrow>
          <mdb-view>
            <img
              src="https://mdbootstrap.com/img/Photos/Horizontal/People/6-col/img%20%283%29.jpg"
              alt="Project"
              class="img-fluid"
            />
            <mdb-mask overlay="white-slight" waves />
          </mdb-view>
          <mdb-card-body class="text-center">
            <mdb-card-title class="font-bold mb-2">
              <strong>{{ firstName }} + {{ lastName }}</strong>
            </mdb-card-title>
            <h5 class="indigo-text" v-if="type=='artist'">
              <strong>Artist</strong>
            </h5>
            <h5 class="indigo-text" v-if="type=='customer'">
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
            <p class="text-justify">
              514-777-7777
            </p>
            <h6 class="text-justify">
              <strong>Email:</strong>
            </h6>
            <p class="text-justify">
              user@email.com
            </p>
            <div class="text-right">
              <mdb-btn outline="primary" rounded size="sm" @click="editProfile">Edit Profile</mdb-btn>
              <mdb-btn outline="primary" rounded size="sm">More...</mdb-btn>
            </div>
          </mdb-card-body>
        </mdb-card>
      </mdb-col>
      <mdb-col md="5" v-if="type == 'artist'">
        <section class="text-center pb-3">
          <mdb-row class="d-flex justify-content-center">
            <mdb-col lg="6" xl="5" class="mb-3">
              <Artwork
                picture="https://mdbootstrap.com/img/Mockups/Horizontal/6-col/pro-profile-page.jpg"
              />
            </mdb-col>
            <mdb-col lg="6" xl="5" class="mb-3">
              <Artwork
                picture="https://mdbootstrap.com/img/Mockups/Horizontal/6-col/pro-signup.jpg"
              />
            </mdb-col>
          </mdb-row>
          <mdb-row class="d-flex justify-content-center">
            <mdb-col lg="6" xl="5" class="mb-3">
              <Artwork
                picture="https://mdbootstrap.com/img/Mockups/Horizontal/6-col/pro-pricing.jpg"
              />
            </mdb-col>
            <mdb-col lg="6" xl="5" class="mb-3">
              <Artwork
                picture="https://mdbootstrap.com/img/Mockups/Horizontal/6-col/pro-landing.jpg"
              />
            </mdb-col>
            <mdb-col class="mb-3">
              <mdb-btn outline="primary" rounded size="sm" @click="addArtwork">Add Artwork</mdb-btn>
            </mdb-col>
            <mdb-col lg="12">
              <div class="text-center">
                <mdb-pagination circle color="blue">
                  <mdb-page-item disabled>First</mdb-page-item>
                  <mdb-page-nav prev></mdb-page-nav>
                  <mdb-page-item active>1</mdb-page-item>
                  <mdb-page-item>2</mdb-page-item>
                  <mdb-page-item>3</mdb-page-item>
                  <mdb-page-item>4</mdb-page-item>
                  <mdb-page-item>5</mdb-page-item>
                  <mdb-page-nav next></mdb-page-nav>
                  <mdb-page-item disabled>Last</mdb-page-item>
                </mdb-pagination>
              </div>
            </mdb-col>
          </mdb-row>
        </section>
      </mdb-col>
      <mdb-col md="4" v-if="type == 'artist'">
        <section class="text-center pb-3">
          <mdb-row class="d-flex justify-content-center">
            <mdb-col lg="6" xl="5" class="mb-3">
              <Transaction />
            </mdb-col>
            <mdb-col lg="6" xl="5" class="mb-3">
              <Transaction />
            </mdb-col>
          </mdb-row>
          <mdb-row class="d-flex justify-content-center">
            <mdb-col lg="6" xl="5" class="mb-3">
              <Transaction />
            </mdb-col>
            <mdb-col lg="6" xl="5" class="mb-3">
              <Transaction />
            </mdb-col>
            <mdb-col lg="12">
              <div class="text-center">
                <mdb-pagination circle color="blue">
                  <mdb-page-item disabled>First</mdb-page-item>
                  <mdb-page-nav prev></mdb-page-nav>
                  <mdb-page-item active>1</mdb-page-item>
                  <mdb-page-item>2</mdb-page-item>
                  <mdb-page-item>3</mdb-page-item>
                  <mdb-page-item>4</mdb-page-item>
                  <mdb-page-item>5</mdb-page-item>
                  <mdb-page-nav next></mdb-page-nav>
                  <mdb-page-item disabled>Last</mdb-page-item>
                </mdb-pagination>
              </div>
            </mdb-col>
          </mdb-row>
        </section>
      </mdb-col>
      <mdb-col md="9" v-if="type == 'customer'">
        <section class="text-center pb-3">
          <mdb-row class="d-flex justify-content-center">
            <mdb-col lg="6" xl="5" class="mb-3">
              <Transaction />
            </mdb-col>
            <mdb-col lg="6" xl="5" class="mb-3">
              <Transaction />
            </mdb-col>
          </mdb-row>
          <mdb-row class="d-flex justify-content-center">
            <mdb-col lg="6" xl="5" class="mb-3">
              <Transaction />
            </mdb-col>
            <mdb-col lg="6" xl="5" class="mb-3">
              <Transaction />
            </mdb-col>
            <mdb-col lg="12">
              <div class="text-center">
                <mdb-pagination circle color="blue">
                  <mdb-page-item disabled>First</mdb-page-item>
                  <mdb-page-nav prev></mdb-page-nav>
                  <mdb-page-item active>1</mdb-page-item>
                  <mdb-page-item>2</mdb-page-item>
                  <mdb-page-item>3</mdb-page-item>
                  <mdb-page-item>4</mdb-page-item>
                  <mdb-page-item>5</mdb-page-item>
                  <mdb-page-nav next></mdb-page-nav>
                  <mdb-page-item disabled>Last</mdb-page-item>
                </mdb-pagination>
              </div>
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
  data() {
    return {
      transaction: "",
      artwork: "",
      firstName: "",
      lastName: "",
      email: "",
      description: "",
      phoneNumber: "",
    };
  },

  async beforeCreate() {
    console.log("path");
    var url = this.$route.fullpath.split("\\");
    var username = "";
    var type = "";
    for (var i = 0; i < 15; i++) {
      if (url[i] === "#") {
        var usernameArray = url[i + 2].split("%20");
        for (var j = 0; j < usernameArray.length; j++) {
          if (j > 0) {
            usernameArray[j] = " " + usernameArray[j];
          }
          username = username + usernameArray[j];
        }
        var type = url[i + 3];
      }
    }
    console.log(username);
    console.log(type);

    var loggedIn = true;
    var frontendUrl = "http://" + config.dev.host + ":" + config.dev.port;
    // had to add this to solve cors problem
    var backendUrl =
      "https://cors-anywhere.herokuapp.com/http://" + config.dev.backendHost;
    var AXIOS = axios.create({
      baseURL: backendUrl,
      headers: { "Access-Control-Allow-Origin": frontendUrl },
    });
    if (type) {
      const response = await AXIOS.post("api/artist/getArtist/" + username, {
        transaction: this.transaction,
        artwork: this.artwork,
        firstName: this.firstName,
        lastName: this.lastName,
        email: this.email,
        description: this.description,
        phoneNumber: this.phoneNumber,
      }).catch((err) => {
        this.status = "Something went wrong";
        loggedIn = false;
      });
    }
  },
  methods: {
    editProfile(){
      window.location.href = '#/editProfile';
      window.scrollTo(0,0);
    },
    addArtwork(){
      window.location.href = '#/addArtwork';
      window.scrollTo(0,0);
    }
  }
};
</script>

<style>
body {
  padding-top: 5rem;
}
</style>
