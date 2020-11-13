<template>
  <!-- Default Home Page -->
  <div>
    <Navbar />
    <img src="../assets/hero-image.jpg" alt="Hero Image" />
    <div>
      <p>
        The art gallery in your neighbourhood has always done business the
        old-fashioned way: connecting local artists with potential customers by
        showcasing their work at the gallery. The current pandemic is forcing
        them to change their business model and focus their business online.
        They hired you to help them design a new software system for them. The
        gallery wants to focus on local artists and encourage them to use their
        website and app to promote and sell their work. Artists register, create
        a profile, and present any pieces they have to sell. Interested
        customers can browse and buy what they like. The gallery can also
        promote artwork available on their premises. Artists can deliver their
        art to customers by mail or can leave it at the store for pickup. The
        gallery makes its income on commission from sold artwork. In teams of
        five students, you will gather requirements, design a multi-tier
        software solution to satisfy those requirements, implement the system,
        validate that the system is satisfying the requirements, and develop a
        release pipeline to automate the software delivery process.
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
  max-width: 100%;
  height: auto;
}
</style>