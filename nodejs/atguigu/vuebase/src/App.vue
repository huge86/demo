<template>
  <div id="app">
    <img src="./assets/logo.png">
    {{ demo }}
    <input v-model="parentText">
    <Learn @getMsg="getSonMsg" :title="parentText" />

    <button @click="changeComponent" type="button" name="button">切换组件</button>

    <keep-alive>
      <component v-bind:is="currentComponent"></component>
    </keep-alive>

  </div>
</template>

<script>
import HelloWorld from './components/HelloWorld'
import VueDemo from "./components/VueDemo"
import Learn from "./components/Learn"
import A from "./components/A"
import B from "./components/B"

export default {
  name: 'App',
  data(){
    return{
      demo:"",
      parentText:"",
      title:{
        name:"iwen"
      },
      currentComponent:A,
      flag:false
    }
  },
  components: {
    HelloWorld,
    VueDemo,
    Learn, // 注入
    A,
    B
  },
  methods:{
    getSonMsg(data){
      this.demo = data
    },
    changeComponent(){
      if(this.flag){
        this.flag = false;
        this.currentComponent = A
      }else{
        this.flag = true;
        this.currentComponent = B
      }

    }
  }
}
</script>

<style>

</style>
