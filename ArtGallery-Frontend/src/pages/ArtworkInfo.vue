<template>
  <div class="class">
    <!-- https://stackoverflow.com/questions/51747817/passing-id-through-router-link-in-vue-js -->
    ArtworkInfo
    <h1>{{ this.$route.fullPath }}</h1>
  </div>
</template>

<script>
export default {
  name: "ArtworkInfo",
};
</script>

<style scoped>
</style>
