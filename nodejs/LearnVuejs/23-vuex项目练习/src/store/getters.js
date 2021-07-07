export default {
  tasks(state) {
    return state.tasks
  },
  unfinishedCount(state, getters) {
    return getters.tasks.reduce((preTotal, task) => {
      return preTotal + (task.isFinished ? 0 : 1)
    }, 0)
  },
  filterTasks(state) {
    return state.tasks.filter(task => {
      switch (state.showIndex) {
        case 0:
          return true
        case 1:
          return !task.isFinished
        case 2:
          return task.isFinished
      }
    })
  }
}
