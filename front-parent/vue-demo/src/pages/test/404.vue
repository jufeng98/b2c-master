<template src="./404.html"></template>
<script>
export default {
  data() {
    return {}
  },
  methods: {},
  mounted() {
    console.log("path not exists:", this.$route.params.pathMatch);
  },
}
</script>
<style scoped>

</style>
