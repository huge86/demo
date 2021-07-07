<template>
  <li :style="{background: bg}"
      @mouseenter="handleStyle(true)" @mouseleave="handleStyle(false)">
    <label for="">
      <input type="checkbox" v-model="todo.complete">
      <span>{{todo.title}}</span>
    </label>
    <button class="btn btn-danger" v-show="isShown" @click="deleteItem">删除</button>
  </li>
</template>

<script>
  export default {
    name: "todoItem",
    props: ['todo', 'index'],
    data() {
      return {
        isShown: false,
        bg: '#fff'
      }
    },
    methods: {
      handleStyle(isEnter) {
        if (isEnter) {
          this.isShown = true
          this.bg = '#ddd'
        } else {
          this.isShown = false
          this.bg = '#fff'
        }
      },
      deleteItem() {
        const {todo, index} = this
        if (window.confirm(`确定删除${todo.title}的评论吗?`)) {
          this.$store.dispatch('removeTodo', index)
        }
      }
    }
  }
</script>

<style scoped>
  li {
    list-style: none;
    height: 36px;
    line-height: 36px;
    padding: 0 5px;
    border-bottom: 1px solid #ddd;
  }

  li label {
    float: left;
    cursor: pointer;
  }

  li label, li input {
    vertical-align: middle;
    margin-right: 6px;
    position: relative;
    top: -1px;
  }

  li button {
    float: right;
    display: none;
    margin-top: 3px;
  }

  li:before {
    content: initial;
  }

  li:last-child {
    border-bottom: none;
  }
</style>
