<template>
  <div class="body">
    <link
      href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css"
      rel="stylesheet"
    />
    <div class="container">
      <div class="col">
        <div class="row">
          <div class="col mb-3">
            <div class="card">
              <div class="card-body">
                <div class="e-profile">
                  <div class="row">
                    <div class="col-12 col-sm-auto mb-3">
                      <div class="mx-auto" style="width: 500px">
                        <div
                          class="d-flex justify-content-center align-items-center rounded"
                          style="
                            height: 500px;
                            background-color: rgb(233, 236, 239);
                          "
                        >
                          <span
                            style="
                              color: rgb(166, 168, 170);
                              font: bold 8pt Arial;
                            "
                            >500x500</span
                          >
                        </div>
                      </div>
                    </div>
                    <div
                      class="col d-flex flex-column flex-sm-row justify-content-between mb-3"
                    >
                      <div class="text-center text-sm-left mb-2 mb-sm-0">
                        <div class="mt-2">
                          <button class="btn btn-primary" type="button">
                            <i class="fa fa-fw fa-camera"></i>
                            <span
                              ><input
                                id="fileUpload"
                                class="btn btn-primary"
                                type="file"
                                name="upload"
                            /></span>
                          </button>
                        </div>
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
                          </div>
                        </div>
                        <div class="row">
                          <div class="col d-flex justify-content-end">
                            <button
                              class="btn btn-primary"
                              type="submit"
                              @click="uploadArtwork"
                            >
                              Submit
                            </button>
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
  name: "AddArtwork",
  data() {
    return {
      artistUsername: "",
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

  methods: {
    async uploadArtwork() {
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
        "api/artist/uploadArtwork",
        {
          name: this.artworkName,
          description: this.artworkDescription,
          forSale: this.forSale,
          isInStore: this.isInStore,
          artist: {
            username: localStorage.getItem("username"),
          },
          artGallery: {
            name: "Online Art Gallery",
          },
        },
        configuration
      ).catch((err) => {
        this.hasError = true;
        this.errorMessage = "Something Went Wrong";
        console.log(err);
      });

      const artworkResponse = await AXIOS.get(
        `api/artwork/getArtwork/${this.artworkName}`,
        configuration
      ).catch((err) => {
        this.hasError = true;
        this.errorMessage = "Something Went Wrong";
      });

      console.log(artworkResponse);

      // get artwork id
      this.artworkId = artworkResponse.data.id;

      var formData = new FormData();
      var imagefile = document.querySelector("#fileUpload");

      const configurationForFileUpload = {
        headers: {
          Authorization: `Bearer ${localStorage.getItem("token")}`,
          "Content-Type": "multiplart/form-data",
        },
      };
      formData.append("file", imagefile.files[0]);
      const uploadImageResponse = await AXIOS.post(
        `api/storage/uploadFile/${this.artworkId}`,
        formData,
        configurationForFileUpload
      ).catch((err) => {
        console.log(err);
      });

      console.log(uploadImageResponse);
    },
  },
};
</script>

<style>
body {
  padding-top: 5rem;
  background: #f8f8f8;
}
input {
  -webkit-appearance: none;
}
</style>