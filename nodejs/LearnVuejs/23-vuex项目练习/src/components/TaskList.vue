<template>
  <div id="task-list">
    <div class="title">任务列表</div>
    <div class="content" v-if="tasks.length">
      <task-item v-for="(task, index) in tasks"
                 :key="task.id"
                 :task="task"></task-item>
    </div>
    <div class="placeholder" v-else>{{showInfo}}</div>
  </div>
</template>

<script>
  import TaskItem from './TaskItem'

  export default {
    name: "TaskList",
    components: {
      TaskItem
    },
    computed: {
      tasks() {
        return this.$store.getters.filterTasks;
      },
      showInfo() {
        switch (this.$store.state.showIndex) {
          case 0:
            return '未添加任务, 请创建新的任务!!!'
          case 1:
            return '没有未完成的任务'
          case 2:
            return '没有已经完成任务'
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

  .content {
    background-color: #fff;
    border-radius: 5px;
    padding: 8px;
  }

  .placeholder {
    text-align: center;
    font-size: 18px;
    font-weight: 700;
    padding: 10px;
    color: #bd362f;
  }
</style>
