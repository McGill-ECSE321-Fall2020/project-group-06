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
              <strong>Alice Mayer</strong>
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
              Lorem ipsum dolor sit amet consectetur, adipisicing elit. Ratione
              perferendis quod animi dignissimos consectetur quibusdam numquam
              laboriosam, minus, provident...
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
    return {};
  },
};
</script>

<style>
body {
  padding-top: 5rem;
}
</style>