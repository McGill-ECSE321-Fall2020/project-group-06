<template>
  <div>
    <Navbar />
    <EditArtwork />
    <Footer />
</div>
</template>

<script>
import Navbar from "../components/Navbar";
import Footer from "../components/Footer";
import EditArtwork from "../components/EditArtwork";
export default {
  name: 'EditArtworkPage',
  components: {
    Navbar,
    EditArtwork,
    Footer,
  },
};
</script>

<style>

</style>