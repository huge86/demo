<template>
  <div class="todo-container">
    <div class="todo-wrap">
      <todo-header></todo-header>
      <todo-main></todo-main>
      <todo-footer></todo-footer>
    </div>
  </div>
</template>

<script>
  import todoHeader from './components/todoHeader.vue'
  import todoMain from './components/todoMain.vue'
  import todoFooter from './components/todoFooter.vue'

  export default {
    name: 'App',
    created () {
      // 模拟异步读取数据
      this.$store.dispatch('readTodo')
    },
    components: {
      todoHeader,
      todoMain,
      todoFooter
    }
  }
</script>

<style>
  .todo-container {
    width: 600px;
    margin: 0 auto;
  }
  .todo-container .todo-wrap {
    padding: 10px;
    border: 1px solid #ddd;
    border-radius: 5px;
  }
</style>
