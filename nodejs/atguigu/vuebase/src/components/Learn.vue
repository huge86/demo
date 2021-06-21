<template lang="html">
  <!-- 只能存在一个根容器 -->
  <div class="container">
    新的组件:{{ title }}
    <input v-model="searchText">
    <button @click="sendMsg" type="button" name="button">传递数据</button>
  </div>
</template>

<script>
export default {
  name:'learn',
  // 所有的初始化状态全部放入data中
  data(){
    return{
      searchText:""
    }
  },
  props:["title"],
  methods:{
    sendMsg(){
      this.$emit("getMsg",this.searchText)
    }
  }
}
</script>

<!-- 样式 -->
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="css" scoped>

.container{
  background: red;
}

</style>
