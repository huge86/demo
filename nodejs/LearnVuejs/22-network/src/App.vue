<template>
  <div id="app">
    <img alt="Vue logo" src="./assets/logo.png">
    <HelloWorld msg="Welcome to Your Vue.js App"/>
  </div>
</template>

<script>
import HelloWorld from './components/HelloWorld.vue'
import jsonp from './network/jsonp'
import axios from './network/axios'

export default {
  name: 'app',
  components: {
    HelloWorld
  },
  created() {
  	// jsonp({
     //  url: 'http://123.207.32.32:8000/home/data',
     //  callback: 'jsonp_abc',
     //  data: {
     //  	type: 'sell',
     //    page: 1
     //  }
    // }).then(result => {
		 //  console.log(result);
	  // }).catch(error => {
		 //  console.log(error);
	  // })
    //
	  // jsonp({
		 //  url: 'http://123.207.32.32:8000/category',
		 //  callback: 'jsonp_cba',
	  // }).then(result => {
		 //  console.log(result);
	  // }).catch(error => {
		 //  console.log(error);
	  // })

    // axios({
    //   url: '/category',
    //   method: 'get',
    // }).then(res => {
	   //  console.log(res);
    // }).catch(err => {
	   //  console.log(err);
    // })

    // 注意: params是get请求拼接在后面的, data是post放在body中的
    axios({
      url: '/home/data',
      method: 'get',
      params: {
      	type: 'sell',
        page: 1
      },
      data: {
      	'name': '王红元'
      }
    }).then(res => {
	    console.log(res);
    }).catch(err => {
	    console.log(err);
    })
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
