<template>
  <div>
    <h1>vue组件五</h1>
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
      count: state => state.moduleB.count
    }),
  },
  methods: {
    increment() {
      this.$store.commit('moduleB/increment')
      console.log("组件五:", this.$store.state.moduleB.count)
    },
    dispatch() {
      this.$store.dispatch('moduleB/increment')
      console.log("组件五:", this.$store.state.moduleB.count)
    }
  },
  mounted() {
  },
}
</script>
<style scoped>

</style>
