const moduleA = {
  state: () => ({count: 0}),
  mutations: {
    increment(state, count) {
      if (count) {
        state.count += count;
      } else {
        state.count++;
      }
    },
  },
  getters: {
    sumWithRootCount(state, getters, rootState) {
      return state.count + rootState.count
    }
  },
  actions: {
    increment({state, commit, rootState}) {
      commit("increment", state.count + rootState.count);
    },
  },
}
export default moduleA
