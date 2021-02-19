<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
    <h2>Essential Links</h2>
    <ul>
      <li v-for="item in bannerList" :key="Math.random()">{{ item.webUrl }}</li>
    </ul>
  </div>
</template>
<script>

export default {
  name: 'HelloWorld',
  data() {
    return {
      msg: 'Welcome to Your Vue.js App',
      bannerList: []
    }
  },
  methods: {
    getBannerList() {
      let self = this
      this.$baseAxios.get(this.$config.GET_BANNER_LIST_URL).then((resJson) => {
        self.bannerList = resJson.bannerList;
      })
    }
  },
  mounted() {
    this.getBannerList()
  },
}
</script>
<style scoped>

</style>
