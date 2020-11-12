<template>
  <div>
    <Navbar />
    <EditProfile />
    <Footer />
  </div>
</template>

<script>
import Navbar from "../components/Navbar";
import Footer from "../components/Footer";
import EditProfile from "../components/EditProfile";
export default {
  name: "EditProfilePage",
  components: {
    Navbar,
    EditProfile,
    Footer,
  },
};
</script>

<style scoped>
</style>