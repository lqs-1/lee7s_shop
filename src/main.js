import Vue from 'vue'
import router from './router'


// 引入ElementUi
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
Vue.use(ElementUI);


// 引入echarts
import echarts from 'echarts'
Vue.prototype.$echarts = echarts


// axios挂载 file系统
import httpRequest from './http/index'
Vue.prototype.httpRequest = httpRequest



Vue.config.productionTip = false

import App from './App.vue'

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
