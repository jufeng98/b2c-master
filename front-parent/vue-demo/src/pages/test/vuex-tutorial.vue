<template>
  <div>
    <vuex1 style="width: 200px"/>
    <vuex2 style="width: 200px"/>
    <vuex3 style="width: 200px"/>
    <vuex4 style="width: 200px"/>
    <vuex5 style="width: 200px"/>
  </div>
</template>
<script>
import vuex1 from "./vuex1";
import vuex2 from "./vuex2";
import vuex3 from "./vuex3";
import vuex4 from "./vuex4";
import vuex5 from "./vuex5";

export default {
  name: 'HelloWorld',
  components: {
    vuex1,
    vuex2,
    vuex3,
    vuex4,
    vuex5
  },
  data() {
    return {}
  },
  methods: {},
  mounted() {
    window.vue = this
  },
}
</script>
<style scoped>

</style>
