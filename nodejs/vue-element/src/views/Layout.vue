<template>
<div>
  <h2>layout布局组件的使用</h2>
  <el-row>
    <el-col :span="24"><div style="border: 1px red solid">一列独占24份:span="24"</div></el-col>
  </el-row>
  <br>
<!--  创建页面中的一行-->
  <el-row>
    <el-col :span="6"><div style="border: 1px green solid">第1列：一列独占6份:span="6"</div></el-col>
    <el-col :span="6"><div style="border: 1px green solid">第2列：一列独占6份:span="6"</div></el-col>
    <el-col :span="6"><div style="border: 1px green solid">第3列：一列独占6份:span="6"</div></el-col>
    <el-col :span="6"><div style="border: 1px green solid">第4列：一列独占6份:span="6"</div></el-col>
  </el-row>

<!--  属性的使用：行数行，列属性-->
<!--行属性演示-->
<!--  gutter与24没有啥关系，不参与总栏数的计算-->
  <br>
  <el-row :gutter="10">
    <el-col :span="8"><div style="border: 1px blue solid">第1列：一列独占8份:span="8"</div></el-col>
    <el-col :span="8"><div style="border: 1px blue solid">第2列：一列独占8份:span="8"</div></el-col>
    <el-col :span="8"><div style="border: 1px blue solid">第3列：一列独占8份:span="8"</div></el-col>
  </el-row>

  <br>
<!--  tag属性指定当前行使用那种标签构建（包裹），默认值div,可以改成span测试下-->

  <!--列属性演示-->
  <h2>layout组件中使用偏移</h2>
<!--  offset属性：即使前面没有元素，也能空出位置-->
  <el-row :gutter="20">
    <el-col :span="6"><div class="grid-content bg-purple">我占用6份</div></el-col>
    <el-col :span="6" :offset="6"><div class="grid-content bg-purple">我占用6份，但我往右偏移了6份，因此我在第三个6份上</div></el-col>
  </el-row>
<!--  offset与push的区别：offset会把后面的元素挤走，push不会;offset相当于margin，push相当于overflow-->
  <el-row :gutter="20">
    <el-col :span="8"><div class="grid-content bg-purple"></div></el-col>
    <el-col :span="4" offset="8" push="4"><div class="grid-content bg-purple"></div></el-col>
  </el-row>
</div>
</template>

<script>
export default {
name: "Layout"
}
</script>

<style scoped>
.el-row {
  margin-bottom: 20px;
&:last-child {
   margin-bottom: 0;
 }
}
.el-col {
  border-radius: 4px;
}
.bg-purple-dark {
  background: #99a9bf;
}
.bg-purple {
  background: #d3dce6;
}
.bg-purple-light {
  background: #e5e9f2;
}
.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
}
</style>