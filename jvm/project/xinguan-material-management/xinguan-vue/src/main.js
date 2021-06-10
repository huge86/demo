import Vue from 'vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import App from './App.vue'
import router from './router'
import moment from 'moment'
import store from './store'
import './assets/css/global.css'

Vue.use(ElementUI);
Vue.config.productionTip = false
Vue.prototype.$moment = moment

Vue.filter('moment', function (value, formatString) {
  formatString = formatString || 'YYYY年MM月DD日';
  // return moment(value).format(formatString); // value可以是普通日期 20170723
  return moment.unix(value).format(formatString); // 这是时间戳转时间
});//标红处为格式的自定义 同样可以YYYY-MM-DD HH:MM:SS ，或者 YYYY/MM/DD

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
