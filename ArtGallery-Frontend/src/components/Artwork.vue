<template>
  <div>
    <div class="artwork-card">
      <img v-bind:src="url" alt="artwork Image" style="width: 100%" />
      <div class="container">
        <h4>
          <b>{{ artworkName }}</b>
        </h4>
        <p>Artist: {{ artistName }}</p>
        <a
          v-bind:href="
            'http://127.0.0.1:8087/#/buyArtwork/' +
            artworkId +
            '/' +
            artworkName +
            '/' +
            artistName
          "
          >More Info</a
        >
        <a class="float-right" @click="editArtwork">Edit Artwork</a>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Artwork",
  props: ["artworkName", "artistName", "artworkId", "url"],
  methods: {
    editArtwork() {
      window.location.href = "/#/editArtwork/" + this.artworkName;
      window.scrollTo(0, 0);
    },
  },
};
</script>

<style scoped>
.artwork-card {
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
  transition: 0.3s;
  width: 100%;
  background-color: #ddd8cc;
}
​ .artwork-card:hover {
  box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2);
}
​ .container {
  padding: 2px 16px;
}
</style>
