<template>
<div>
  <h2>Link文字链接</h2>
  <div>
    <el-link>默认链接</el-link>
<!--    type属性-->
    <el-link type="primary">主要链接</el-link>
<!--    禁用状态：文字链接不可用状态-->
    <el-link type="success" disabled>成功链接</el-link>
<!--    下划线：文字链接下划线-->
    <div>
      <el-link :underline="false">无下划线</el-link>
      <el-link>有下划线</el-link>
    </div>
<!--    图标：带图标的文字链接可增强辨识度-->
    <div>
      <el-link icon="el-icon-edit">编辑</el-link>
      <el-link>查看<i class="el-icon-view el-icon--right"></i> </el-link>
    </div>
<!--    href target-->
    <el-link href="https://element.eleme.io" target="_blank">点我跳转到：https://element.eleme.io</el-link>
  </div>

</div>
</template>

<script>
export default {
  name: "Link"
}
</script>

<style scoped>

</style>