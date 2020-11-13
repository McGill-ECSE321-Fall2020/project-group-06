<template>
  <div>
    <img src="../assets/hero-image.jpg" alt="Hero Image" />
    <div class="centered"><strong>WELCOME TO THE <br> ONLINE ART GALLERY</strong></div>
  </div>
</template>

<script>
export default {
  name: "Hero",
};
</script>

<style scoped>
img {
  width: 100%;
  height: auto;
}
.centered {
  color: black;
  position: absolute;
  top: 35%;
  font-size: large;
  left: 50%;
  transform: translate(-50%, -50%);
  text-indent: 7%;
  font-family: "Times New Roman", Times, serif;
}
</style>
