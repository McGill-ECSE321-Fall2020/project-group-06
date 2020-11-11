import Vue from "vue";
import Router from "vue-router";
import Hello from "@/components/Hello";
import Home from "@/pages/Home";
import Login from "@/pages/Login";
import Artworks from "@/pages/Artworks"
import Signup from "@/pages/Signup"

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
    },{
      path: "/login",
      name: "Login",
      component: Login
    },{
      path: "/artworks",
      name: "Artworks",
      component: Artworks
    }, {
      path: "/signup",
      name: "Signup",
      component: Signup
    }
  ]
});
