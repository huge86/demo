<template>
  <div id="app">
<!--    注释掉脚手架默认生成的组件-->
    <div id="nav">
      <router-link to="/">Home</router-link> |
      <router-link to="/about">About</router-link> |
      <router-link to="/button">点我显示button</router-link>
    </div>

    <div>
      <router-link to="/buttondetails">Button:按钮组件的详细使用</router-link> |
    </div>
    <div>
      <router-link to="/link">Link:文字链接</router-link> |
    </div>
    <div>
      <router-link to="/layout">Layout:布局组件的使用</router-link> |
    </div>
    <div>
      <router-link to="/container">Container:布局容器组件的使用</router-link> |
    </div>
    <div>
      <router-link to="/radio">Radio:Radio组件的使用</router-link> |
    </div>
    <div>
      <router-link to="/checkbox">Checkbox:Checkbox组件的使用</router-link> |
    </div>
    <div>
      <router-link to="/input">Input:Input组件的使用</router-link> |
    </div>
    <router-view/>
  </div>
</template>

<style lang="scss">
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;

  a {
    font-weight: bold;
    color: #2c3e50;

    &.router-link-exact-active {
      color: #42b983;
    }
  }
}
</style>
