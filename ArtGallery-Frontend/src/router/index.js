import Vue from "vue";
import Router from "vue-router";
import Hello from "@/components/Hello";
import Home from "@/pages/Home";
import Login from "@/pages/Login";

import Artworks from "@/pages/Artworks";
import Signup from "@/pages/Signup";
import ArtworkInfo from "@/pages/ArtworkInfo";
import Customer from "@/pages/CustomerPage";
import Artist from "@/pages/ArtistPage";
import BuyArtwork from "@/pages/BuyArtwork";
import EditProfile from "@/pages/EditProfilePage";
import AddArtwork from "@/pages/AddArtworkPage";

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: "/hello",
      name: "Hello",
      component: Hello
    },
    {
      path: "/",
      name: "Home",
      component: Home
    },
    {
      path: "/login",
      name: "Login",
      component: Login
    },
    {
      path: "/artworks",
      name: "Artworks",
      component: Artworks
    },
    {
      path: "/signup",
      name: "Signup",
      component: Signup
    },
    {
      path: "/artworkinfo/:artworkId/:artworkName/:artistName",
      name: "ArtworkInfo",
      component: ArtworkInfo
    },
    {
      path: "/customer",
      name: "Customer",
      component: Customer
    },
    {
      path: "/artist/:username/:type",
      name: "Artist",
      component: Artist
    },
    {
      path: "/buyArtwork/:artworkId/:artworkName/:artistName",
      name: "BuyArtwork",
      component: BuyArtwork
    },
    {
      path: "/editProfile",
      name: "EditProfile",
      component: EditProfile
    },
    {
      path: "/addArtwork",
      name: "AddArtwork",
      component: AddArtwork
    }
  ]
});
