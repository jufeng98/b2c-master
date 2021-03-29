<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
    <h2>Essential Links</h2>
    <ul>
      <li>
        <router-link to="/foo">Go to none exists</router-link>
      </li>
      <li>
        <router-link to="/asyncTemplate">Go to asyncTemplate</router-link>
      </li>
      <li>
        <router-link to="/mainIndex">Go to none mainIndex</router-link>
      </li>
      <li>
        <router-link to="/login">Go to login</router-link>
        <router-link to="/userLogin">Go to login</router-link>
      </li>
      <li>
        <router-link to="/template">Go to none template</router-link>
        <router-link to="/templateA-example">Go to templateA*</router-link>
        <router-link to="/templateB-example">Go to templateB*</router-link>
        <router-link to="/templateC-example">Go to templateC*</router-link>
      </li>
      <li>
        <router-link to="/elementTable">Go to elementTable(link)</router-link>
        <router-link :to="{ name: 'elementTable', params: { userId: 123 }}">Go to elementTable(to)</router-link>
        <el-button type="primary" @click="goToPagePath">Go to elementTable(path)</el-button>
        <el-button type="primary" @click="goToPageName">Go to elementTable(name)</el-button>
        <el-button type="primary" @click="goToPageReplace">Go to elementTable(replace)</el-button>
      </li>
      <li>
        <router-link to="/user/liangyudong">Go to user1</router-link>
        <router-link to="/user/jufeng98">Go to user2</router-link>
      </li>
    </ul>
  </div>
</template>
<script>
import gzip from 'gzip-js';

import contentHtml from "../../../static/h5-long.html"
import contentJs from "../../../static/h5-long.umd"
// 加入 !   前缀禁用配置文件中的普通loader，比如：require("!raw!./script.coffee")
// 加入 !!  前缀禁用配置文件中所有的loader，比如：require("!!raw!./script.coffee")
// 加入 -!  前缀禁用配置文件中的pre loader和普通loader，但是不包括post loader，比如：require("-!raw!./script.coffee")
import contentCss from "-!raw-loader!../../../static/h5-long.css"

export default {
  name: 'HelloWorld',
  data() {
    return {
      msg: 'Welcome to Your Vue.js App',
    }
  },
  methods: {
    goToPagePath() {
      // 带查询参数，变成 /elementTable?plan=private
      this.$router.push({path: '/elementTable', query: {plan: 'private'}})
    },
    goToPageName() {
      // 带查询参数，变成 /elementTable?plan=private
      this.$router.push({name: 'elementTable', params: {plan: 'private'}})
    },
    goToPageReplace() {
      // 带查询参数，变成 /elementTable?plan=private
      this.$router.replace({name: 'elementTable', params: {plan: 'private'}})
    },
  },
  mounted() {
    window.vue = this;
    window.html = contentHtml;
    window.js = contentJs;
    window.css = contentCss;
    let options = {
      level: 9,
    };
    window.out = gzip.zip(contentHtml, options);
  },
}
</script>
<style scoped>

</style>
