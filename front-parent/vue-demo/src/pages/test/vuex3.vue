<template>
  <div>
    <h1>vue组件三</h1>
    <el-button type="primary" @click="increment">点击(同步)</el-button>
    <hr/>
    <el-button type="success" @click="incrementAsync">点击(异步)</el-button>
    <hr/>
    <el-button type="success" @click="incrementPromise">点击(异步Promise)</el-button>
    <hr/>
    <el-button type="success" @click="incrementAsyncFunc">点击(异步async)</el-button>
    <div>计数:{{ count }}</div>
    <div>已完成的事件数量:{{ doneTodosCount }}</div>
  </div>
</template>
<script>
import {mapActions, mapGetters, mapState} from "vuex";

export default {
  name: 'HelloWorld',
  data() {
    return {}
  },
  computed: {
    // mapState 辅助函数帮助我们生成计算属性
    ...mapState({
      count: state => state.count
    }),
    // mapGetters 辅助函数仅仅是将 store 中的 getter 映射到局部计算属性
    ...mapGetters([
      'doneTodosCount',
    ])
  },
  methods: {
    ...mapActions([
      'increment', // 将 `this.increment()` 映射为 `this.$store.dispatch('increment')`
    ]),
    incrementAsync() {
      this.$store.dispatch('incrementAsync', {amount: 6})
      console.log("组件三:", this.$store.state.count)
    },
    incrementPromise() {
      this.$store.dispatch('actionA')
        .then((res) => {
          console.log("actionA已完成,开始接下来动作", res)
        })
        .catch((e) => {
          console.log("actionA已失败,开始接下来动作", e)
        })
    },
    incrementAsyncFunc() {
      this.$store.dispatch('actionD')
    }
  },
  mounted() {
  },
}
</script>
<style scoped>

</style>
