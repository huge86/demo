<template>
  <div id="create-task">
    <div class="title">添加任务</div>
    <input type="text" placeholder="提示: 输入任务, 按回车键添加任务"
           v-model="title" @keyup.enter="addTask">
  </div>
</template>

<script>
  import {ADD_TASK} from "../store/types";

  export default {
    name: "CreateTask",
    data() {
      return {
        title: null
      }
    },
    methods: {
      addTask() {
        // 1.获取任务
        const title = this.title && this.title.trim()

        // 2.判断用户是否有输入内容
        if (title) {
          // 2.1.创建任务对象
          const firstE = this.$store.state.tasks[0];
          const id = firstE ? firstE.id + 1: 0
          const task = {
            id: id,
            title,
            isFinished: false
          }

          // 2.2.添加到Vuex
          this.$store.commit(ADD_TASK, {task})

          // 2.3.将输入框为null
          this.title = null
        } else {
          alert('请输入对应的任务!!!')
        }
      }
    }
  }
</script>

<style scoped>
  .title {
    font-size: 16px;
    font-weight: 700;
    margin: 15px 0;
  }

  input {
    width: 100%;
    height: 35px;
    padding-left: 8px;
    box-sizing: border-box;
  }
</style>
