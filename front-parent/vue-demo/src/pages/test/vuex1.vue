<template>
  <div>
    <h1>vue组件一</h1>
    <el-button type="primary" @click="increment">点击(同步)</el-button>
    <div>计数:{{ count }}</div>
    <div>已完成的事件数量:{{ doneTodosCount }}</div>
    <div>根据事件id获取事件id:{{todo.id}},text:{{todo.text}}</div>
  </div>
</template>
<script>
import {mapState} from "vuex";

export default {
  name: 'HelloWorld',
  data() {
    return {
      todo: this.$store.getters.getTodoById(2)
    }
  },
  computed: {
    ...mapState({
      count: state => state.count
    }),
    doneTodosCount () {
      return this.$store.getters.doneTodosCount
    }
  },
  methods: {
    increment() {
      this.$store.commit('increment')
      console.log("组件一:", this.$store.state.count)
    }
  },
  mounted() {
  },
}
</script>
<style scoped>

</style>
