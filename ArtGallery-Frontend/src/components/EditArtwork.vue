<template>
  <div class="body">
    <link
      href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css"
      rel="stylesheet"
    />
    <div v-if="username != artwork.artist.username && username != 'admin'">
      You cannot edit this artwork as you are not its owner
    </div>
    <div class="container" v-if="artwork.artist.username == username || username == 'admin'">
      <div class="col">
        <div class="row">
          <div class="col mb-3">
            <div class="card">
              <div class="card-body">
                <div class="e-profile">
                  <div class="row">
                    <div class="col-12 col-sm-auto mb-3">
                      <div class="mx-auto" style="width: 500px">
                        <img v-bind:src="url" alt="artwork image" />
                      </div>
                    </div>
                  </div>
                  <ul class="nav nav-tabs">
                    <li class="nav-item">
                      <a href="" class="active nav-link">Parameters</a>
                    </li>
                  </ul>
                  <div class="tab-content pt-3">
                    <div class="tab-pane active">
                      <form class="form" novalidate="">
                        <div class="row">
                          <div class="col">
                            <div class="row">
                              <div class="col">
                                <div class="form-group">
                                  <label>Name</label>
                                  <input
                                    class="form-control"
                                    type="text"
                                    v-model="artworkName"
                                  />
                                </div>
                              </div>
                              <div class="col">
                                <div class="form-group">
                                  <label>Type of Artwork</label>
                                  <select
                                    class="form-control"
                                    name="typeOfArtwork"
                                  >
                                    <option value="Sculpture">Sculpture</option>
                                    <option value="Painting">Painting</option>
                                    <option value="Photography">
                                      Photography
                                    </option>
                                    <option value="Other">Other</option>
                                  </select>
                                </div>
                              </div>
                            </div>
                            <div class="row">
                              <div class="col">
                                <div class="form-group">
                                  <label>Price</label>
                                  <input
                                    class="form-control"
                                    type="text"
                                    v-model="price"
                                    placeholder="$100.00"
                                  />
                                </div>
                              </div>
                            </div>
                            <div class="row">
                              <div class="col mb-3">
                                <div class="form-group">
                                  <label>Description</label>
                                  <textarea
                                    class="form-control"
                                    rows="5"
                                    v-model="artworkDescription"
                                  ></textarea>
                                </div>
                              </div>
                            </div>
                            <div class="row">
                              <div class="col">
                                <div class="form-group">
                                  <label>Is In Store?</label>
                                  <select class="form-control" name="isInStore">
                                    <option value="Yes">Yes</option>
                                    <option value="No">No</option>
                                  </select>
                                </div>
                              </div>
                            </div>
                            <div v-if="hasError">
                              <div style="color: red">
                                {{ errorMessage }}
                              </div>
                            </div>
                            <div v-if="!hasError">
                              <div style="color: green">
                                {{ errorMessage }}
                              </div>
                            </div>
                          </div>
                        </div>
                        <div class="row">
                          <div class="col d-flex">
                            <input
                              type="button"
                              class="btn btn-danger"
                              @click="removeArtwork"
                              value="Remove"
                            />
                          </div>
                          <div class="col d-flex justify-content-end">
                            <input
                              class="btn btn-primary"
                              type="button"
                              @click="editArtwork"
                              value="Submit"
                            />
                          </div>
                        </div>
                      </form>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="col-12 col-md-3 mb-3">
            <div class="card mb-3">
              <div class="card-body">
                <div class="px-xl-3">
                  <button class="btn btn-block btn-secondary">
                    <i class="fa fa-sign-out"></i>
                    <span>Logout</span>
                  </button>
                </div>
              </div>
            </div>
            <div class="card">
              <div class="card-body">
                <h6 class="card-title font-weight-bold">Support</h6>
                <p class="card-text">
                  Get fast, free help from our friendly assistants.
                </p>
                <button type="button" class="btn btn-primary">
                  Contact Us
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
var config = require("../../config");
export default {
  name: "EditArtwork",
  data() {
    return {
      username: "",
      artworkName: "",
      artworkDescription: "",
      forSale: true,
      isInStore: false,
      price: "",
      hasError: false,
      errorMessage: "",
      url: "",
      artworkId: "",
    };
  },
  async beforeCreate() {
    const configuration = {
      headers: {
        Authorization: `Bearer ${localStorage.getItem("token")}`,
      },
    };
    var frontendUrl = "http://" + config.dev.host + ":" + config.dev.port;
    var backendUrl =
      "https://cors-anywhere.herokuapp.com/http://" + config.dev.backendHost;
    var AXIOS = axios.create({
      baseURL: backendUrl,
      headers: { "Access-Control-Allow-Origin": frontendUrl },
    });

    var name = this.$route.fullPath.split("/")[2].replace(/%20/g, " ");
    console.log(name);
    const promise = await AXIOS.get(
      "api/artwork/getArtwork/" + name,
      configuration
    ).catch((err) => {
      console.log(err);
    });
    // populate the array
    this.artwork = promise.data;
    console.log(this.artwork);
    this.price = this.artwork.price;
    this.isInStore = this.artwork.isInStore;
    this.artworkDescription = this.artwork.description;
    this.typeArtwork = this.artwork.typeOfArtwork;
    this.artworkName = this.artwork.name;
    this.url = this.artwork.url;
    this.username = localStorage.getItem("username");
  },
  methods: {
    async editArtwork() {
      console.log("editing artwork");
      const configuration = {
        headers: {
          Authorization: `Bearer ${localStorage.getItem("token")}`,
        },
      };
      var frontendUrl = "http://" + config.dev.host + ":" + config.dev.port;
      var backendUrl =
        "https://cors-anywhere.herokuapp.com/http://" + config.dev.backendHost;
      var AXIOS = axios.create({
        baseURL: backendUrl,
        headers: { "Access-Control-Allow-Origin": frontendUrl },
      });

      const response = await AXIOS.put(
        "api/artwork/updateArtwork",
        {
          name: this.artworkName,
          description: this.artworkDescription,
          forSale: this.forSale,
          isInStore: this.isInStore,
          id: this.artwork.id,
          price: this.price,
          artist: {
            username: localStorage.getItem("username"),
            id: this.artwork.artist.id,
          },
          artGallery: {
            name: "Online Art Gallery",
            id: 8988,
          },
        },
        configuration
      ).catch((err) => {
        this.hasError = true;
        this.errorMessage = "Something Went Wrong";
        console.log(err);
      });
      if (!this.hasError) {
        this.errorMessage = "Successfully edited!";
      }
    },
    async removeArtwork() {
      console.log("removing artwork");
      const configuration = {
        headers: {
          Authorization: `Bearer ${localStorage.getItem("token")}`,
        },
      };
      var frontendUrl = "http://" + config.dev.host + ":" + config.dev.port;
      var backendUrl =
        "https://cors-anywhere.herokuapp.com/http://" + config.dev.backendHost;
      var AXIOS = axios.create({
        baseURL: backendUrl,
        headers: { "Access-Control-Allow-Origin": frontendUrl },
      });
      const response = await AXIOS.post(
        "api/artist/removeArtwork/" + this.artwork.id,
        {},
        configuration
      ).catch((err) => {
        this.hasError = true;
        this.errorMessage = "Something Went Wrong";
        console.log(err);
      });
      if (!this.hasError) {
        this.errorMessage = "Successfully removed!";
      }
    },
  },
};
</script>

<style scoped>
body {
  padding-top: 5rem;
  background: #f8f8f8;
}
</style>