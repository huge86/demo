<template>
<div>
  <h2>Radio组件的使用</h2>
<!--  v-model与label要配合使用-->
  <el-radio v-model="label" label="男" >男</el-radio>
  <el-radio v-model="label" label="女">女</el-radio>

<!-- Radio按钮属性的使用-->
<!--  disabled属性-->
  <el-radio v-model="value" label="是" disabled >是</el-radio>
  <el-radio v-model="value" label="否">否</el-radio>

  <!--  border属性，是否显示边框-->
  <el-radio v-model="display" label="显示边框" border >显示边框</el-radio>
  <el-radio v-model="display" label="隐藏边框" border>隐藏边框</el-radio>
<!--  其他属性：size,name,提交表单时，根据name属性，获取单选按钮组传递的值-->
<!--  radio事件的使用：事件在使用时必须使用vue中绑定事件方式进行，例如@事件名=事件处理函数-->
  <!--  change事件-->
  <el-radio v-model="real" label="真" @change = "change">真</el-radio>
  <el-radio v-model="real" label="假" @change = "change">假</el-radio>

<!--  单选框组：适用于在多个互斥的选项中选择的场景-->
  <!--  按钮组的属性：size/disabled-->
  <el-radio-group v-model="radio">
    <el-radio :label="3">备选项3</el-radio>
    <el-radio :label="6">备选项6</el-radio>
    <el-radio :label="9">备选项9</el-radio>
  </el-radio-group>

<!--按钮样式单选按钮组-->
  <!--  按钮组的属性：label/name/size/disabled/text-color/fill-->
  <el-radio-group v-model="radio1">
    <el-radio-button label="上海"></el-radio-button>
    <el-radio-button label="北京"></el-radio-button>
    <el-radio-button label="广州"></el-radio-button>
    <el-radio-button label="深圳"></el-radio-button>
  </el-radio-group>

</div>
</template>

<script>
export default {
  name: "Radio",
  data(){
    return{
      label:"男",
      value:"否",
      display:"显示边框",
      real: '假',
      radio: 3,
      radio1:"北京"
    }
  },
  methods:{
    change(){
      console.log(this.real);
    }
  }
}
</script>

<style scoped>

</style>