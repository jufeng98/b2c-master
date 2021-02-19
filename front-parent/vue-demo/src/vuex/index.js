import Vue from 'vue'
import Vuex from 'vuex'
import moduleA from "./moduleA"
import moduleB from "./moduleB";
import debugPlugin from "./debugPlugin";
import createLogger from 'vuex/dist/logger'

Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
    moduleA,
    moduleB
  },
  plugins: [
    debugPlugin,
    createLogger
  ],
  state: {
    count: 0,
    todos: [
      {id: 1, text: 'text1', done: true},
      {id: 2, text: 'text2', done: false}
    ]
  },
  // 需要从 store 中的 state 中派生出一些状态,可定义“getter”（可以认为是 store 的计算属性）
  // getter 的返回值会根据它的依赖被缓存起来，且只有当它的依赖值发生了改变才会被重新计算。
  getters: {
    doneTodos: state => {
      return state.todos.filter(todo => todo.done)
    },
    doneTodosCount: (state, getters) => {
      return getters.doneTodos.length
    },
    getTodoById: (state) => (id) => {
      return state.todos.find(todo => todo.id === id)
    }
  },
  mutations: {
    increment(state) {
      state.count++
    },
    incrementWithParam(state, payload) {
      state.count += payload.amount
    }
  },
  actions: {
    increment(context) {
      context.commit('increment')
    },
    incrementAsync({commit}, payload) {
      setTimeout(() => {
        commit('incrementWithParam', payload)
      }, 2000)
    },
    actionA({commit}) {
      return new Promise((resolve, reject) => {
        let a = parseInt(Math.random() * 10 + "")
        if (a % 2 === 0) {
          setTimeout(() => {
            commit('increment')
            resolve('success')
          }, 1000);
        } else {
          reject(new Error("错误"))
        }
      })
    },
    actionB({dispatch, commit}) {
      return dispatch('actionA').then(() => {
        commit('increment')
      })
    },
    async actionC({commit}) {
      let getData = function () {
        return new Promise((resolve) => {
          setTimeout(function () {
            resolve({amount: 10})
          }, 2000)
        })
      }
      commit('incrementWithParam', await getData())
    },
    async actionD({dispatch, commit}) {
      // 等待 actionC 完成
      await dispatch('actionC')
      let getData = function () {
        return new Promise((resolve) => {
          setTimeout(function () {
            resolve({amount: 8})
          }, 2000)
        })
      }
      commit('incrementWithParam', await getData())
    }
  },
})
