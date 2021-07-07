<template>
<div>
  <h2>Checkbox组件的使用</h2>
  <!-- `checked` 为 true 或 false -->
  <el-checkbox v-model="checked">北京</el-checkbox>
<!--  属性列表：https://element.eleme.cn/#/zh-CN/component/checkbox-->

  <el-checkbox v-model="checked1" disabled>备选项1</el-checkbox>
  <el-checkbox v-model="checked2" disabled>备选项</el-checkbox>

  <br>
<!--  多选框组-->
  <el-checkbox-group v-model="checkList" @change="aaa" :min="1">
    <el-checkbox label="复选框 A"></el-checkbox>
    <el-checkbox label="复选框 B"></el-checkbox>
    <el-checkbox label="复选框 C"></el-checkbox>
<!--按钮样式的多选组合-->
  </el-checkbox-group>
</div>
</template>

<script>
export default {
  name: "CheckBox",
  data() {
    return {
      checked: true,
      checked1: false,
      checked2: true,
      checkList:["复选框 A","复选框 B"]
    };
  },
  methods:{
    aaa(){
      console.log(this.checkList);
    }
  }
}
</script>

<style scoped>

</style>