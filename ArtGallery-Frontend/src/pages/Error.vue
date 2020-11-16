<template>
  <!-- purple x moss 2020 -->
  <div>
    <div class="mainbox">
      <div class="err">404</div>
      <i class="far fa-question-circle fa-spin"></i>
      <div class="msg">
        Maybe this page moved? Got deleted? Is hiding out in quarantine? Never
        existed in the first place?
        <p>Let's go <a href="#/">home</a> and try from there.</p>
      </div>
    </div>
  </div>
</template>


<script>
export default {
  name: "Error",
};
</script>

<style scoped>
body {
  background-color: #95c2de;
}

.mainbox {
  background-color: #95c2de;
  margin: auto;
  height: 600px;
  width: 600px;
  position: relative;
}

.err {
  color: #ffffff;
  font-family: "Nunito Sans", sans-serif;
  font-size: 11rem;
  position: absolute;
  left: 20%;
  top: 8%;
}

.far {
  position: absolute;
  font-size: 8.5rem;
  left: 42%;
  top: 15%;
  color: #ffffff;
}

.err2 {
  color: #ffffff;
  font-family: "Nunito Sans", sans-serif;
  font-size: 11rem;
  position: absolute;
  left: 68%;
  top: 8%;
}

.msg {
  text-align: center;
  font-family: "Nunito Sans", sans-serif;
  font-size: 1.6rem;
  position: absolute;
  left: 16%;
  top: 45%;
  width: 75%;
}

a {
  text-decoration: none;
  color: white;
}

a:hover {
  text-decoration: underline;
}
</style>