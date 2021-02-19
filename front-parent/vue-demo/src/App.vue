<template>
  <div id="app">
    <!--如果 router-view 没有设置名字，那么默认为 default-->
    <router-view class="view-default"/>
    <router-view class="view-two" name="a"></router-view>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
