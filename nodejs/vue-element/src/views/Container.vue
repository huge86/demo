<template>
<div>
  <h2>1、容器组件基本的使用</h2>
<!--  创建容器-->
<!--  不加样式，会错边，并且aside与main不在同一行-->
  <el-container>
    <el-header>
      <div style="border: red solid 1px">
      我是标题
      </div>
    </el-header>
    <el-aside>
      <div style="border: green solid 1px">
        我是菜单
      </div>
    </el-aside>
    <el-main>
      <div style="border: yellow solid 1px">
        我是main工作区
      </div>
    </el-main>
  </el-container>

  <h2>2、容器的嵌套</h2>
  <el-container>
    <el-header>
      <div style="border: red solid 1px">
        我是标题
      </div>
    </el-header>
    <el-container>
      <el-aside>
        <div style="border: green solid 1px">
          我是菜单
        </div>
      </el-aside>
      <el-main>
        <div style="border: yellow solid 1px">
          我是main工作区
        </div>
      </el-main>
    </el-container>
  </el-container>

  <h2>3、容器中使用样式美化</h2>
  <el-container>
    <el-header>
      <div style="border: red solid 1px">
        我是标题
      </div>
    </el-header>
    <el-container>
      <el-aside>
        <div style="border: green solid 1px">
          我是菜单
        </div>
      </el-aside>
      <el-main>
        <div style="border: yellow solid 1px">
          我是main工作区
        </div>
      </el-main>
    </el-container>
    <el-footer>
      <div style="border: blue solid 1px">
        我是footer
      </div>
    </el-footer>
  </el-container>

<!--  容器组件的属性-->
</div>
</template>

<script>
export default {
  name: "Container"
}
</script>

<style scoped>
.el-header, .el-footer {
  background-color: #B3C0D1;
  color: #333;
  text-align: center;
  line-height: 60px;
}

.el-aside {
  background-color: #D3DCE6;
  color: #333;
  text-align: center;
  line-height: 200px;
}

.el-main {
  background-color: #E9EEF3;
  color: #333;
  text-align: center;
  line-height: 160px;
}

body > .el-container {
  margin-bottom: 40px;
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
  line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
  line-height: 320px;
}
</style>