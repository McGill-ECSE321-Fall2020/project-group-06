<template>
<div>
    <Navbar />
    <Profile type="customer"/>
    <Footer />
</div>
</template>

<script>
import Navbar from "../components/Navbar";
import Footer from "../components/Footer";
import Profile from "../components/Profile";
export default {
  name: 'Customer',
  components: {
    Navbar,
    Profile,
    Footer,
  },
};
</script>

<style>
</style>