<template>
  <div>
    <img src="../assets/login-hero.jpg" alt="Login Hero Image" />
    <div class="container">
      <div class="centered-text">
        <b>Login to your account</b>
        <br />
        <br />
        <div class="form-group">
          <input
            type="text"
            class="form-control"
            v-model="username"
            placeholder="username"
          />
        </div>
        <div class="form-group">
          <input
            type="password"
            class="form-control"
            v-model="password"
            placeholder="password"
          />
          <a href="#">forgot password?</a>
        </div>
        <button>Login</button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "LoginHero",
  data() {
    return {
      username: "",
      password: ""
    };
  }
};
</script>

<style scoped>
* {
  box-sizing: border-box;
}
img {
  max-width: 100%;
  height: auto;
}
input {
  background-color: white;
}
.centered-text {
  position: absolute;
  top: 29%;
  font-size: large;

  left: 63%;
  transform: translate(-50%, -50%);
}
a {
  font-size: small;
}
button {
  background-color: #ddd8cc;
  border: none;
  color: black;
  padding: 15px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  opacity: 0.5;
  border-radius: 12px;
}
button:hover {
  opacity: 1;
}
</style>
