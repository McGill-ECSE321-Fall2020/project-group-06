<template>
  <!-- Default Home Page -->
  <div>
    <Navbar />
    <img src="../assets/about-image.jpg" alt="About Image" />
    <div>
      <h1>OUR STORY</h1>
      <p class="centered">
        Your neighbourhood art gallery has always done business the
        old-fashioned way: connecting local artists with potential customers by
        showcasing their work at the physical gallery. The current pandemic is
        forcing us to change our business model and focus our business online.
        The gallery wants to focus on local artists and encourage them to use
        our website and app to promote and sell their work. Artists register,
        create a profile, and present any pieces they have to sell. Interested
        customers can browse and buy what they like. The gallery can also
        promote artwork available on their premises. Artists can deliver their
        art to customers by mail or can leave it at the store for pickup. The
        gallery makes its income on commission from sold artwork.
      </p>
    </div>
    <Footer />
  </div>
</template>

<script>
// components imports
import Navbar from "../components/Navbar";
import Hero from "../components/Hero";
import Footer from "../components/Footer";

export default {
  name: "Home",
  components: {
    Navbar,
    Hero,
    Footer,
  },
};
</script>

<style scoped>
img {
  width: 100%;
  height: auto;
  align-content: space-around;
}
.centered {
  text-align: justify;
  color: black;
  top: 50%;
  font-size: large;
  left: 50%;
  /* transform: translate(-50%, -50%); */
  padding: 10%;
  text-indent: 3%;
}
h1 {
  position: absolute;
  text-align: center;
  top: 75%;
  left: 50%;
  /* bring your own prefixes */
  transform: translate(-50%, -50%);
  font-family: "Times New Roman", Times, serif;
}
</style>