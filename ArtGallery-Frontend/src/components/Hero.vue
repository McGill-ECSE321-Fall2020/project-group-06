<template>
  <div>
    <img src="../assets/hero-image.jpg" alt="Hero Image" />
    <div class="centered">WELCOME TO ART GALLERY</div>
  </div>
</template>

<script>
export default {
  name: "Hero"
};
</script>

<style scoped>
img {
  max-width: 100%;
  height: auto;
}
.centered {
  position: absolute;
  top: 35%;
  font-size: large;
  left: 50%;
  transform: translate(-50%, -50%);
}
</style>
