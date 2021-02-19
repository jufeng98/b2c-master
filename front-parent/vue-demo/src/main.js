// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
// 引入ElementUI
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import baseAxios from './common/baseRequest'
import config from './config'
import './mock/mock.js'
import store from './vuex'

Vue.prototype.$baseAxios = baseAxios
Vue.prototype.$config = config
Vue.config.productionTip = false
Vue.use(ElementUI)

/* eslint-disable no-new */
let vm = new Vue({
  el: '#app',
  router,
  store,
  components: {App},
  template: '<App/>'
})
window.vue = vm
