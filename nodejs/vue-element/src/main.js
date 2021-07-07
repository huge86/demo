import Vue from 'vue'
import App from './App.vue'
import './registerServiceWorker'
import router from './router'
import store from './store'
// 导入elementui和css
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

// 使用组件
Vue.use(ElementUI);
Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
