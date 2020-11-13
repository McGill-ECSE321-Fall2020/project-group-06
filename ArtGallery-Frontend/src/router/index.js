import Vue from "vue";
import Router from "vue-router";
import Hello from "@/components/Hello";
import Home from "@/pages/Home";
import Login from "@/pages/Login";
import About from "@/pages/About";

import Artworks from "@/pages/Artworks";
import Signup from "@/pages/Signup";
import ArtworkInfo from "@/pages/ArtworkInfo";
import BuyArtwork from "@/pages/BuyArtwork";
import EditProfile from "@/pages/EditProfilePage";
import AddArtwork from "@/pages/AddArtworkPage";
import Profile from "@/pages/ProfilePage";

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
    },
    {
      path: "/aboutUs",
      name: "About",
      component: About
    },
    {
      path: "/profile",
      name: "Profile",
      component: Profile
    }
  ]
});
