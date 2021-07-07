const TODOS_KEY = 'todos'

export default {
  fetch() {
    return JSON.parse(localStorage.getItem(TODOS_KEY) || '[]')
  },
  save(todos) {
    localStorage.setItem(TODOS_KEY, JSON.stringify(todos))
  }
}
