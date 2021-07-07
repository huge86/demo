<template>
  <ul class="todo-main">
    <todo-item v-for="(todo, index) in todos"
               :todo="todo"
               :key="index"></todo-item>
  </ul>
</template>

<script>
  import todoItem from './todoItem'
  import storageUtil from '../util/storageUtil'

  export default {
    name: "todoMain",
    components: {
      todoItem
    },
    computed: {
      todos() {
        return this.$store.getters.todos
      }
    },
    watch: {
      todos: {
        handler: storageUtil.save,
        deep: true
      }
    }
  }
</script>

<style scoped>
  .todo-main {
    margin-left: 0px;
    border: 1px solid #ddd;
    border-radius: 2px;
    padding: 0px;
  }
  .todo-empty {
    height: 40px;
    line-height: 40px;
    border: 1px solid #ddd;
    border-radius: 2px;
    padding-left: 5px;
    margin-top: 10px;
  }
</style>
