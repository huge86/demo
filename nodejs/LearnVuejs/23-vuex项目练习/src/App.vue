<template>
  <div id="app">
    <task-header></task-header>
    <div class="container">
      <task-create></task-create>
      <task-select></task-select>
      <task-list></task-list>
    </div>
  </div>
</template>

<script>
  import TaskHeader from './components/TaskHeader'
  import TaskCreate from './components/TaskCreate'
  import TaskSelect from './components/TaskSelect'
  import TaskList from './components/TaskList'

  export default {
    name: 'App',
    components: {
      TaskList,
      TaskHeader,
      TaskCreate,
      TaskSelect
    },
    data() {
      return {
        selectIndex: 0
      }
    }
  }
</script>

<style>
  #app .container{
    width: 800px;
    height: 500px;
    background-color: #e6edef;
    margin: 0 auto;
    padding: 10px;
    border-radius: 5px;
    overflow: hidden;
  }
</style>
