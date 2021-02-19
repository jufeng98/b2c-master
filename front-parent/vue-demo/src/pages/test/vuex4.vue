<template>
  <div>
    <h1>vue组件四</h1>
    <el-button type="primary" @click="increment">点击(同步)</el-button>
    <hr/>
    <el-button type="primary" @click="dispatch">点击(dispatch)</el-button>
    <div>计数:{{ count }}</div>
  </div>
</template>
<script>
import {mapState} from "vuex";

export default {
  name: 'HelloWorld',
  data() {
    return {}
  },
  computed: {
    ...mapState({
      count: state => state.moduleA.count
    }),
  },
  methods: {
    increment() {
      this.$store.commit('increment')
      console.log("组件四:", this.$store.state.moduleA.count)
    },
    dispatch() {
      this.$store.dispatch('increment')
      console.log("组件四:", this.$store.state.moduleA.count)
    }
  },
  mounted() {
  },
}
</script>
<style scoped>

</style>
