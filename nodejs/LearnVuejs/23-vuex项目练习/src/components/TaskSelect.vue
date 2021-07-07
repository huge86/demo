<template>
  <div id="select-task" class="clear-fix">
    <div class="hint-info">未完成任务: {{unfinishedCount}}个</div>
    <div class="task-tab ">
      <span v-for="(title, index) in titles"
            :key="index"
            @click="itemClick(index)"
            :class="{active: index===currentIndex}">{{title}}</span>
    </div>
  </div>
</template>

<script>
  import {SHOW_INDEX} from "../store/types";

  export default {
    name: "TaskSelect",
    data() {
      return {
        titles: ['所有任务', '未完成任务', '已完成任务'],
        currentIndex: 0
      }
    },
    computed: {
      unfinishedCount() {
        return this.$store.getters.unfinishedCount
      }
    },
    methods: {
      itemClick(index) {
        this.currentIndex = index
        this.$store.commit(SHOW_INDEX, index)
      }
    }
  }
</script>

<style scoped>
  #select-task {
    margin-top: 15px;
  }

  .hint-info {
    color: #bd362f;
    float: left;
    margin-left: 5px;
  }

  .task-tab {
    float: right;
  }

  .task-tab span {
    margin:0 8px;
    cursor: pointer;
    padding: 5px;
  }

  .task-tab span.active {
    color: #bd362f;
  }

  .task-tab span:hover {
    border-bottom: 2px solid #bd362f;
  }

  .clear-fix::after {
    content:"";
    clear:both;
    display:block;
    height:0;
    overflow:hidden;
    visibility:hidden;
  }
</style>
