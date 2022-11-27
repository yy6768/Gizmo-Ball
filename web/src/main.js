import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import './plugins/element.js'

Vue.use(ElementUI);
Vue.config.productionTip = false

//引入axios依赖
import axios from 'axios'
//让请求携带上浏览器的cookie
axios.defaults.withCredentials=true
Vue.prototype.$axios = axios


new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
