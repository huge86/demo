<template>
<div>
  <h2>Input组件的使用</h2>
  <el-input v-model="name" placeholder="请输入内容"></el-input>
<!--  可清空：clearable,输入框后面有个可清空图标-->
  <el-input v-model="name" placeholder="请输入内容" clearable></el-input>

<!--  show-password：使用show-password属性即可得到一个可切换显示隐藏的密码框-->

  <el-input v-model="isPassword" placeholder="请输入内容" show-password></el-input>
<!--  带 icon 的输入框:可以通过 prefix-icon 和 suffix-icon 属性在 input 组件首部和尾部增加显示图标，也可以通过 slot 来放置图标-->
  <div class="demo-input-suffix">
    属性方式：
    <el-input
        placeholder="请选择日期"
        suffix-icon="el-icon-date"
        v-model="input1">
    </el-input>
    <el-input
        placeholder="请输入内容"
        prefix-icon="el-icon-search"
        v-model="input2">
    </el-input>
  </div>
  <div class="demo-input-suffix">
    slot 方式：
    <el-input
        placeholder="请选择日期"
        v-model="input3">
      <i slot="suffix" class="el-input__icon el-icon-date"></i>
    </el-input>
    <el-input
        placeholder="请输入内容"
        v-model="input4">
      <i slot="prefix" class="el-input__icon el-icon-search"></i>
    </el-input>
  </div>
<!--  文本域:用于输入多行文本信息，通过将 type 属性的值指定为 textarea,文本域高度可通过 rows 属性控制-->
  <el-input
      type="textarea"
      :rows="2"
      placeholder="请输入内容"
      v-model="textarea">
  </el-input>
<!--  可自适应文本高度的文本域:通过设置 autosize 属性可以使得文本域的高度能够根据文本内容自动进行调整，并且 autosize 还可以设定为一个对象，指定最小行数和最大行数。-->
  <el-input
      type="textarea"
      autosize
      placeholder="请输入内容"
      v-model="textarea1">
  </el-input>
  <div style="margin: 20px 0;"></div>
  <el-input
      type="textarea"
      :autosize="{ minRows: 2, maxRows: 4}"
      placeholder="请输入内容"
      v-model="textarea2">
  </el-input>
<!--  复合型输入框:可前置或后置元素，一般为标签或按钮,可通过 slot 来指定在 input 中前置或者后置内容。-->
<!--  带输入建议:根据输入内容提供对应的输入建议-->
<!--  input 方法-->
<!--  属性及事件参考：https://element.eleme.cn/#/zh-CN/component/input-->

</div>
</template>

<script>
export default {
  name: "Input",
  data(){
    return{
      name:'',
      isPassword:"",
      input1: '',
      input2: '',
      input3: '',
      input4: '',
      textarea: '',
      textarea1: '',
      textarea2: ''
    }
  }
}
</script>

<style scoped>

</style>