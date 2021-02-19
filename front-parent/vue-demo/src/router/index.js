import Vue from 'vue'
import Router from 'vue-router'
/*
@符号表示是在本地文件系统引入文件,@代表源代码目录,一般是src,引入@的目的是避免../../此类丑陋的语法
 */
import index from '@/pages/test/index'
import template from '@/pages/test/template'
import elementTable from '@/pages/test/elementTable'
import elementForm from '@/pages/test/elementForm'
import HelloWorld from '@/components/HelloWorld'
import login from '@/pages/users/login'
import mainIndex from '@/pages/mainIndex'

const asyncTemplate = () => import("../pages/test/asyncTemplate");

Vue.use(Router)

export default new Router({
  routes: [
    {path: '/', name: 'index', component: index},
    {path: '/template', name: 'template', component: template},
    {path: '/templateA*', redirect: '/elementTable'},
    {path: '/templateB*', redirect: {name: 'elementTable'}},
    {
      path: '/templateC*', redirect: to => {
        // 方法接收 目标路由 作为参数
        // return 重定向的 字符串路径/路径对象
        console.log("redirect:", to);
        const {hash, params, query} = to
        if (query.to === 'foo') {
          return {path: '/foo', query: null}
        }
        if (hash === '#baz') {
          return {name: 'baz', hash: ''}
        }
        if (params.id) {
          return '/with-params/:id'
        } else {
          return "/elementTable";
        }
      }
    },
    {path: '/elementTable', name: 'elementTable', component: elementTable},
    {path: '/elementForm', name: 'elementForm', component: elementForm},
    {path: '/HelloWorld', name: 'HelloWorld', component: HelloWorld},
    {path: '/asyncTemplate', name: 'asyncTemplate', component: asyncTemplate},
    {path: '/login', name: 'login', component: login, alias: '/userLogin'},
    {path: '/mainIndex', name: 'mainIndex', components: {a: mainIndex}},
    {path: '/vuex-tutorial', name: 'vuex-tutorial', component: () => import('../pages/test/vuex-tutorial')},
    {path: '/user/:username', name: 'user', component: () => import('../pages/test/user')},
    {path: '*', name: '404', component: () => import('../pages/test/404')},
  ]
})
