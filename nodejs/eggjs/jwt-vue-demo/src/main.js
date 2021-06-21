import Vue from 'vue'
import App from './App.vue'
import router from './router'
const axios = require('axios');


Vue.config.productionTip = false

Vue.prototype.$http = axios;




new Vue({
  router,
  render: function (h) { return h(App) }
}).$mount('#app')
