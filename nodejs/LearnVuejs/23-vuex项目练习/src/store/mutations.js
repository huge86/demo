import {ADD_TASK, UPDATE_TASK, DELETE_TASK, SHOW_INDEX} from "./types";

export default {
  [ADD_TASK](state, {task}) {
    state.tasks.unshift(task)
  },
  [UPDATE_TASK](state, {id, finished}) {
    for (let index in state.tasks) {
      if (state.tasks[index].id === id) {
        state.tasks[index].isFinished = finished
        break
      }
    }
  },
  [DELETE_TASK](state, id) {
    for (let index in state.tasks) {
      if (state.tasks[index].id === id) {
        state.tasks.splice(index, 1)
        break
      }
    }
  },
  [SHOW_INDEX](state, selectIndex) {
    state.showIndex = selectIndex
  }
}
