<template>
  <div>
    <Navbar />
    <LoginHero />
    <Footer />
  </div>
</template>

<script>
import Navbar from "../components/Navbar";
import Footer from "../components/Footer";
import LoginHero from "../components/LoginHero";
export default {
  name: "Login",
  components: {
    Navbar,
    LoginHero,
    Footer,
  },
};
</script>

<style scoped></style>
