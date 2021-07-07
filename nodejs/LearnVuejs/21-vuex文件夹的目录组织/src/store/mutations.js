import {RECEIVE_TODOS, ADD_TODO, DELETE_DONE, REMOVE_TODO, UPDATE_ALL_TODOS} from "./types";

export default {
  [RECEIVE_TODOS](state, {todos}) {
    state.todos = todos
  },
  [ADD_TODO](state, {todo}) {
    state.todos.unshift(todo)
  },
  [REMOVE_TODO](state, {index}) {
    state.todos.splice(index, 1)
  },
  [DELETE_DONE](state, {isCheck}) {
    state.todos.forEach(todo => {
      todo.complete == isCheck
    })
  }
}
