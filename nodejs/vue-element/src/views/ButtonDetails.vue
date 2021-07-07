<template>
<div>
  <h2>按钮属性的使用</h2>
<!--  在elementUI中所有组件的属性全部写在组件标签上-->
<!--  使用type、plain、round和circle属性来定义 Button 的样式-->
  <el-row>
    <el-button type="primary">type属性，预设了几种样式</el-button>
    <el-button type="primary" disabled>禁用按钮，添加disabled属性即可</el-button>
<!--    plain属性，布尔值，默认false,用于说明是否是普通按钮-->
    <el-button type="primary" plain="true">是否朴素按钮</el-button>
<!--    没有边框和背景色的按钮-->
    <el-button type="text">文字按钮</el-button>
<!--    带图标的按钮可增强辨识度（有文字）或节省空间（无文字）-->
    <el-button type="primary" icon="el-icon-search">搜索</el-button>
<!--    也可以设置在文字右边的 icon ，只要使用i标签即可，可以使用自定义图标-->
    <el-button type="primary">上传<i class="el-icon-upload el-icon--right"></i></el-button>

  </el-row>
  <br>
  <hr>
  <el-row>
    <!--    按钮组：以按钮组的方式出现，常用于多项类似操作-->
    <el-button-group>
      <el-button type="primary" icon="el-icon-arrow-left">上一页</el-button>
      <el-button type="primary">下一页<i class="el-icon-arrow-right el-icon--right"></i></el-button>
    </el-button-group>
    <el-button-group>
      <el-button type="primary" icon="el-icon-edit"></el-button>
      <el-button type="primary" icon="el-icon-share"></el-button>
      <el-button type="primary" icon="el-icon-delete"></el-button>
    </el-button-group>
  </el-row>

  <br>
  <hr>
  <!--    加载中...-->
<!--  要设置为 loading 状态，只要设置loading属性为true即可-->
  <el-row>

    <el-button type="primary" :loading="true">加载中</el-button>
  </el-row>
<!--  size:Button 组件提供除了默认值以外的三种尺寸，可以在不同场景下选择合适的按钮尺寸-->
<!--  额外的尺寸：medium、small、mini，通过设置size属性来配置它们-->
  <br>
  <hr>
  <el-row>
    <el-button>默认按钮</el-button>
    <el-button size="medium">中等按钮</el-button>
    <el-button size="small">小型按钮</el-button>
    <el-button size="mini">超小按钮</el-button>
  </el-row>
  <el-row>
    <el-button round>默认按钮</el-button>
    <el-button size="medium" round>中等按钮</el-button>
    <el-button size="small" round>小型按钮</el-button>
    <el-button size="mini" round>超小按钮</el-button>
  </el-row>
</div>
</template>

<script>
export default {
name: "ButtonDetails"
}
</script>

<style scoped>

</style>