<template>
  <div>
    <div class="artwork-card">
      <img
        :src="resolve_img_url(picture)"
        alt="Random Artwork"
        style="width:100%;"
      />
      <div class="container">
        <h4><b>Mona Lisa</b></h4>
        <p>Leonardo da Vinky</p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Artwork",
  props: ['picture'],
  methods: {
    resolve_img_url: function (path) {
      if(path.startsWith('http')){
        return path;
      }
      let images = require.context('../assets/', false, /\.png$|\.jpg$/)
      return images("./" + path);
    }
  }
}

</script>

<style>
.artwork-card {
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
  transition: 0.3s;
  width: 100%;
  background-color: #ddd8cc;
}
​ .artwork-card:hover {
  box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2);
}
​ .container {
  padding: 2px 16px;
}
</style>
