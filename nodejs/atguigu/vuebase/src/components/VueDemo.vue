<template lang="html">
  <div>
    <div class="old" :class="{active:classDemo,'txt':classDemo}">
      css
    </div>
    <div :class="cssObj">
        test
    </div>
    <button @click="changeCss" type="button" name="button">改变css</button>
    <div :class="[isActive ?activeClass:''  ,errorClass]">
      test1
    </div>
    <!-- 对象中只能接受字符串，数组中可以接受对象 -->
    <div class="old" :class="[{ helloC:classDemo} , isActive ? active+'12' : 'noactive']">
      应用中的样式
    </div>
    <div :style="{ color:color,fontSize:'40px' }">
      style
    </div>

    <div class="myinput">
      <input v-model.trim="inputmsg" type="text" name="" value="">
      <p>{{ inputmsg }}</p>
      <input type="checkbox" id="checkbox" v-model="checked">
      <label for="checkbox">{{ checked }}</label>
    </div>
    <button @click="changeName" type="button" name="button">改变name</button>
    <p>{{ myname }}</p>
  </div>
</template>

<script>
export default {
  name:"vuedemo",
  data(){
    return{
      checked:true,
      color:"green",
      msg:"hello",
      classDemo:true,
      active:"active1",
      cssObj:{
        'c1':true,
        'c2':true
      },
      activeClass: 'active',
      errorClass: 'text-danger',
      isActive:true,
      helloC:"hc-",
      inputmsg:"默认数据",
      myname:"iwen"
    }
  },
  // 实时监听数据变化
  watch:{
    inputmsg(data){
      if(data == "100"){
        this.inputmsg = "符合条件"
      }
    },
    myname(data){
      console.log(data);
    }
  },
  // 计算属性
  computed:{
    getMsg(){
      // 如果数据没有被改变，则不会重新计算
      return this.msg.split('').reverse().join('')
    }
  },
  methods:{
    mMsg(){
      return this.msg.split('').reverse().join('')
    },
    changeCss(){
      this.cssObj = {
        "c1":false,
        "c2":true
      }
    },
    changeName(){
      this.myname += "1";
    }
  }
}
</script>

<style lang="css">

.active{
  color: red;
}

.txt{
  font-size: 30px;
}

</style>
