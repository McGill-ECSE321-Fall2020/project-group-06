<template>
<div>
    <Navbar />
    <Profile type="artist"/>
    <Footer />
</div>
</template>

<script>
import Navbar from "../components/Navbar";
import Footer from "../components/Footer";
import Profile from "../components/Profile";
export default {
  name: 'Artist',
  components: {
    Navbar,
    Profile,
    Footer,
  },
};
</script>

<style>
</style>