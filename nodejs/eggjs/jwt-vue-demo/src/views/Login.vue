<template>
  <div class="login">
    <h2>登录</h2>

    <button @click="doLogin">执行登录</button>
  </div>
</template>



<script>
export default {
  name: "home",
  data() {
    return {
      msg: "this is about"
    };
  },
  methods: {
    doLogin() {
      this.$http
        .get("http://localhost:3000/api/login")
        .then(function(response) {         
          console.log(response.data.token);

          //保存用户信息 和 token
          localStorage.setItem('token',response.data.token);
        })
        .catch(function(error) {        
          console.log(error);
        });
       
    }
  }
};
</script>