<template>
  <div id="task-item">
    <input type="checkbox" v-model="isFinished">
    <span :class="{finished: isFinished}">{{task.title}}</span>
    <button class="delete" @click="deleteTask">删除</button>
  </div>
</template>

<script>
  import {UPDATE_TASK, DELETE_TASK} from "../store/types";

  export default {
    name: "TaskItem",
    props: {
      task: {
        type: Object,
        default: {}
      }
    },
    data() {
      return {
        isFinished: this.task.isFinished
      }
    },
    methods: {
      deleteTask() {
        this.$store.commit(DELETE_TASK, this.task.id)
      }
    },
    watch: {
      isFinished(newValue) {
        this.$store.commit(UPDATE_TASK, {id: this.task.id, finished: newValue})
      }
    }
  }
</script>

<style scoped>
  #task-item {
    border-bottom: 1px solid #e6edef;
    padding: 5px;
  }

  #task-item:hover .delete {
    visibility: visible;
  }

  .finished {
    text-decoration: line-through;
    color: #888
  }

  .delete {
    visibility: hidden;
    float: right;
  }
</style>
