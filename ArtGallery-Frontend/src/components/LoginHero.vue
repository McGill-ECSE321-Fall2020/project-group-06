<template>
  <div>
    <img src="../assets/login-hero.jpg" alt="Login Hero Image" />

    <div class="centered-text">Please Sign in.</div>
  </div>
</template>

<script>
export default {
  name: "LoginHero"
};
</script>

<style scoped>
img {
  max-width: 100%;
  height: auto;
}
.centered-text {
  position: absolute;
  top: 10%;
  font-size: large;
  left: 50%;
  transform: translate(-50%, -50%);
}
</style>
