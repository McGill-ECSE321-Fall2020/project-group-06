<template>
  <div class="body">
    <link
      href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css"
      rel="stylesheet"
    />
    <div class="container">
      <div class="row flex-lg-nowrap">
        <div class="col-12 col-lg-auto mb-3" style="width: 200px">
          <div class="card p-3">
            <div class="e-navlist e-navlist--active-bg">
              <ul class="nav">
                <li class="nav-item">
                  <a class="nav-link px-2 active" href="./overview.html"
                    ><i class="fa fa-fw fa-bar-chart mr-1"></i
                    ><span>Overview</span></a
                  >
                </li>
                <li class="nav-item">
                  <a class="nav-link px-2" href="./users.html"
                    ><i class="fa fa-fw fa-th mr-1"></i><span>CRUD</span></a
                  >
                </li>
                <li class="nav-item">
                  <a class="nav-link px-2" href="./settings.html"
                    ><i class="fa fa-fw fa-cog mr-1"></i
                    ><span>Settings</span></a
                  >
                </li>
              </ul>
            </div>
          </div>
        </div>

        <div class="col">
          <div class="row">
            <div class="col mb-3">
              <div class="card">
                <div class="card-body">
                  <div class="e-profile">
                    <div class="row">
                      <div class="col-12 col-sm-auto mb-3">
                        <div class="mx-auto" style="width: 140px">
                          <div
                            class="d-flex justify-content-center align-items-center rounded"
                            style="
                              height: 140px;
                              background-color: rgb(233, 236, 239);
                            "
                          >
                            <span
                              style="
                                color: rgb(166, 168, 170);
                                font: bold 8pt Arial;
                              "
                              >140x140</span
                            >
                          </div>
                        </div>
                      </div>
                      <div
                        class="col d-flex flex-column flex-sm-row justify-content-between mb-3"
                      >
                        <div class="text-center text-sm-left mb-2 mb-sm-0">
                          <h4 class="pt-sm-2 pb-1 mb-0 text-nowrap">
                            John Smith
                          </h4>
                          <p class="mb-0">@johnny.s</p>
                          <div class="text-muted">
                            <small>Last seen 2 hours ago</small>
                          </div>
                          <div class="mt-2">
                            <button class="btn btn-primary" type="button">
                              <i class="fa fa-fw fa-camera"></i>
                              <span>Change Photo</span>
                            </button>
                          </div>
                        </div>
                        <div class="text-center text-sm-right">
                          <span class="badge badge-secondary">{{
                            AccountType
                          }}</span>
                          <div class="text-muted">
                            <small>Joined 09 Dec 2017</small>
                          </div>
                        </div>
                      </div>
                    </div>
                    <ul class="nav nav-tabs">
                      <li class="nav-item">
                        <a href="" class="active nav-link">Settings</a>
                      </li>
                    </ul>
                    <div class="tab-content pt-3">
                      <div class="tab-pane active">
                        <form @submit.prevent="handleSaveChanges">
                          <div class="row">
                            <div class="col">
                              <div class="row">
                                <div class="col">
                                  <div class="form-group">
                                    <label>First Name</label>
                                    <input
                                      class="form-control"
                                      type="text"
                                      v-model="firstName"
                                    />
                                  </div>
                                  <div class="form-group">
                                    <label>Last Name</label>
                                    <input
                                      class="form-control"
                                      type="text"
                                      v-model="lastName"
                                    />
                                  </div>
                                </div>
                                <div class="col">
                                  <div class="form-group">
                                    <label>Username</label>
                                    <input
                                      class="form-control"
                                      type="text"
                                      v-model="username"
                                    />
                                  </div>
                                </div>
                              </div>
                              <div class="row">
                                <div class="col">
                                  <div class="form-group">
                                    <label>Email</label>
                                    <input
                                      class="form-control"
                                      type="email"
                                      v-model="email"
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
                                      v-model="description"
                                    ></textarea>
                                  </div>
                                </div>
                              </div>
                              <div class="row">
                                <div class="col">
                                  <div class="form-group">
                                    <label>Phone Number</label>
                                    <input
                                      class="form-control"
                                      type="text"
                                      v-model="phoneNumber"
                                    />
                                  </div>
                                </div>
                              </div>
                            </div>
                          </div>
                          <div class="row">
                            <div class="col-12 col-sm-6 mb-3">
                              <div class="mb-2"><b>Change Password</b></div>
                              <div class="row">
                                <div class="col">
                                  <div class="form-group">
                                    <label>Current Password</label>
                                    <input
                                      class="form-control"
                                      type="password"
                                      placeholder="••••••"
                                    />
                                  </div>
                                </div>
                              </div>
                              <div class="row">
                                <div class="col">
                                  <div class="form-group">
                                    <label>New Password</label>
                                    <input
                                      class="form-control"
                                      type="password"
                                      placeholder="••••••"
                                    />
                                  </div>
                                </div>
                              </div>
                              <div class="row">
                                <div class="col">
                                  <div class="form-group">
                                    <label
                                      >Confirm
                                      <span class="d-none d-xl-inline"
                                        >Password</span
                                      ></label
                                    >
                                    <input
                                      class="form-control"
                                      type="password"
                                      placeholder="••••••"
                                    />
                                  </div>
                                </div>
                              </div>
                            </div>
                            <div class="col-12 col-sm-5 offset-sm-1 mb-3">
                              <div class="mb-2"><b>Keeping in Touch</b></div>
                              <div class="row">
                                <div class="col">
                                  <label>Email Notifications</label>
                                  <div class="custom-controls-stacked px-2">
                                    <div class="custom-control custom-checkbox">
                                      <input
                                        type="checkbox"
                                        class="custom-control-input"
                                        id="notifications-blog"
                                        checked=""
                                      />
                                      <label
                                        class="custom-control-label"
                                        for="notifications-blog"
                                        >Blog posts</label
                                      >
                                    </div>
                                    <div class="custom-control custom-checkbox">
                                      <input
                                        type="checkbox"
                                        class="custom-control-input"
                                        id="notifications-news"
                                        checked=""
                                      />
                                      <label
                                        class="custom-control-label"
                                        for="notifications-news"
                                        >Newsletter</label
                                      >
                                    </div>
                                    <div class="custom-control custom-checkbox">
                                      <input
                                        type="checkbox"
                                        class="custom-control-input"
                                        id="notifications-offers"
                                        checked=""
                                      />
                                      <label
                                        class="custom-control-label"
                                        for="notifications-offers"
                                        >Personal Offers</label
                                      >
                                    </div>
                                  </div>
                                </div>
                              </div>
                            </div>
                          </div>
                          <div class="row">
                            <div class="col d-flex justify-content-end">
                              <button class="btn btn-primary" type="submit">
                                Save Changes
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
  </div>
</template>

<script>
import axios from "axios";
var config = require("../../config");
export default {
  async beforeCreate() {
    console.log("Before edit profile");
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
    this.firstName = response.data.firstName;
    this.lastName = response.data.lastName;
    this.password = response.data.password;
    this.username = response.data.username;
    this.email = response.data.email;
    this.description = response.data.description;
    this.phoneNumber = response.data.phoneNumber;
    this.bankAccountNumber = response.data.bankAccountNumber;
    this.creditCardNumber = response.data.creditCardNumber;
    
    if (!this.creditCardNumber && !this.bankAccountNumber) {
      this.AccountType = "Administrator"
    }
    else if (this.creditCardNumber) {
      this.AccountType = "Customer"
    }
    else if (this.bankAccountNumber) {
      this.AccountType = "Artist"
    }
  },
  name: "EditProfile",
  data() {
    return {
      AccountType: "Administrator",
      firstName: "",
      lastName: "",
      password: "",
      username: "",
      email: "",
      description: "",
      phoneNumber: "",
      bankAccountNumber: "",
      creditCardNumber: "",
      newPassword: "",
      confirmNewPassword: ""
    };
  },
  methods: {
    async handleSaveChanges() {
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
      const response = await AXIOS.put("api/user/updateUser", {
        firstName: this.firstName,
        lastName: this.lastName,
        password: this.password,
        username: this.username,
        email: this.email,
        description: this.description,
        phoneNumber: this.phoneNumber,
        creditCardNumber: this.creditCardNumber
      }, configuration).catch((err) => {
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
.body {
  padding-top: 5rem;
  background: #f8f8f8;
}
</style>