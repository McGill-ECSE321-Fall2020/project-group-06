import Vue from "vue";
import Router from "vue-router";
import Hello from "@/components/Hello";
import Home from "@/pages/Home";
import Navbar from "@/components/Navbar";

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: "/",
      name: "Hello",
      component: Hello
    },
    {
      path: "/home",
      name: "Home",
      component: Home
    }
  ]
});
