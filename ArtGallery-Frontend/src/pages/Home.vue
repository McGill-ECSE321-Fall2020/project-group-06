<template>
 <!-- Default Home Page -->
<div>
    <Navbar />
    <Hero />
    <Footer />
</div>
</template>

<script>
// components imports
import Navbar from "../components/Navbar"
import Hero from "../components/Hero"
import Footer from "../components/Footer"

export default {
    name: "Home",
    components : {
        Navbar,
        Hero,
        Footer,
    }
}
</script>

<style scoped>

</style>