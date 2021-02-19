const moduleB = {
  state: () => ({count: 0}),
  mutations: {
    increment(state) {
      state.count++
    },
  },
  getters: {
    sumWithRootCount(state, getters, rootState, rootGetters) {
      return state.count + rootState.count + rootGetters.doneTodos
    }
  },
  actions: {
    increment(context) {
      context.commit('increment')
    },
  },
}
export default {
  // 带命名空间的模块
  namespaced: true,
  state: moduleB.state,
  mutations: moduleB.mutations,
  actions: moduleB.actions
}
