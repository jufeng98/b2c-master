<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
    <h2>Essential Links</h2>
    <router-link to="/user/jufeng98">Go to user2</router-link>
  </div>
</template>
<script>

export default {
  name: 'HelloWorld',
  data() {
    return {
      msg: '欢迎来到用户页面:' + this.$route.params.username,
    }
  },
  methods: {},
  beforeRouteUpdate(to, from, next) {
    // react to route changes...
    // don't forget to call next()
    console.log("route change:", to, from, next);
  },
  mounted() {
  },
}
</script>
<style scoped>

</style>
