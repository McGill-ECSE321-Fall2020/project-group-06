<template>
  <mdb-card cascade narrow class="d-flex mb-5">
    <mdb-view>
      <img :src="picture"
        alt="Project"
        class="img-fluid"
      />
      <mdb-mask overlay="white-slight" waves />
    </mdb-view>
    <mdb-card-body>
      <mdb-card-title class="font-bold mb-3">
        <strong>Artwork name</strong>
      </mdb-card-title>
      <mdb-card-text
        >Some quick example text to build on the card title and make up the bulk
        of the card's content.</mdb-card-text
      >
    </mdb-card-body>
    <mdb-card-footer class="links-light profile-card-footer">
      <span class="right">
        <a class="p-2" href="#profile">
          Live Preview
          <mdb-icon icon="photo" class="ml-1" />
        </a>
      </span>
    </mdb-card-footer>
  </mdb-card>
</template>

<script>
import {  mdbCard, mdbCardBody, mdbView, mdbMask, mdbCardTitle, mdbCardText, mdbCardFooter, mdbIcon } from 'mdbvue'
export default {
    name: 'ArtworkCard',
    props: ['picture'],
    components: {
    mdbCard,
    mdbCardBody,
    mdbView,
    mdbMask,
    mdbCardTitle,
    mdbCardText,
    mdbCardFooter,
    mdbIcon
  },
  data() {
    return {};
  },
};
</script>

<style scoped>
.profile-card-footer {
  background-color: #F7F7F7 !important;
  padding: 1.25rem;
}
.card.card-cascade .view {
  box-shadow: 0 3px 10px 0 rgba(0, 0, 0, 0.15), 0 3px 12px 0 rgba(0, 0, 0, 0.15);
}
</style>