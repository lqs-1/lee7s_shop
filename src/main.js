import Vue from 'vue'
import router from './router'

// 引入buefy
import Buefy from 'buefy';
import 'buefy/dist/buefy.css';
Vue.use(Buefy);


// 引入ElementUi
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
Vue.use(ElementUI);


Vue.config.productionTip = false

// axios挂载 file系统
import httpRequest from './http/index'
Vue.prototype.httpRequest = httpRequest


import App from './App.vue'
new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
