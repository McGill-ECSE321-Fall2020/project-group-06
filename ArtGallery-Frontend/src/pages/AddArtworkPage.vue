<template>
  <div>
    <Navbar />
    <AddArtwork />
    <Footer />
</div>
</template>

<script>
import Navbar from "../components/Navbar";
import Footer from "../components/Footer";
import AddArtwork from "../components/AddArtwork";
export default {
  name: 'AddArtworkPage',
  components: {
    Navbar,
    AddArtwork,
    Footer,
  },
};
</script>

<style>

</style>