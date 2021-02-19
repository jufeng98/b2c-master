<template>
  <div>
    <h1>vue组件二</h1>
    <el-button type="primary" @click="increment">点击(同步)</el-button>
    <div>计数:{{ count }}</div>
    <div>已完成的事件数量:{{ doneTodosCount }}</div>
  </div>
</template>
<script>
import {mapGetters, mapState} from "vuex";

export default {
  name: 'HelloWorld',
  data() {
    return {}
  },
  computed: {
    ...mapState({
      count: state => state.count
    }),
    ...mapGetters([
      'doneTodosCount',
    ])
  },
  methods: {
    increment() {
      this.$store.commit('incrementWithParam', {amount: 3})
      console.log("组件二:", this.$store.state.count)
    }
  },
  mounted() {
  },
}
</script>
<style scoped>

</style>
