<template>
  <div class="nav-container">
    <div class="icon-container">
      <el-image class="icon-img" :src="icon" :fit="fit"></el-image>
    </div>

    <el-menu
      mode="horizontal"
      :background-color="variables.menuBg"
      :text-color="variables.menuText"
      :active-text-color="variables.menuActiveText"
      :default-active="activeMenu"
      :router="true"
    >
      <el-menu-item index="tags" >标签管理</el-menu-item>
      <el-menu-item index="tasks">任务管理</el-menu-item>
    </el-menu>
  </div>
</template>

<script>
import variables from "../styles/variables.scss";

export default {
  computed: {
    icon() {
      return require("../assets/logo.png")
    },
    fit() {
      return "contain"
    },
    variables() {
      return variables;
    },
    activeMenu() {
      const route = this.$route
    }
  }
};
</script>

<style lang="scss" scoped>
@import "~@/styles/variables.scss";

.nav-container {
  height: auto;
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  align-items: center;
  background-color: $menuBg;

  .icon-container {

    .icon-img {
      height: 40px;
      width: 150px;
    }
  }

  .el-menu {
    width: fit-content;

    .el-menu-item {
      &:hover {
        // you can use $subMenuHover
        background-color: $menuHover !important;
      }
    }
  }
}
</style>