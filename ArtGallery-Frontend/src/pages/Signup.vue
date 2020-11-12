<template>
    <div>
        <Navbar />
        <SignUpHero />
        <Footer />
    </div>
</template>

<script>
import Navbar from "../components/Navbar";
import Footer from "../components/Footer";
import SignUpHero from "../components/SignUpHero";
export default {
    name: "Signup",
    components: {
        Navbar,
        SignUpHero,
        Footer
    }
};
</script>

<style>
</style>