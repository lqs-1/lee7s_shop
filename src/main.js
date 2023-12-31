import Vue from 'vue'
import router from './router'


// 引入elementUI  npm install element-ui
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
Vue.use(ElementUI);

// // 引入ant-design
// import Antd from 'ant-design-vue'
// import 'ant-design-vue/dist/antd.css'
// Vue.use(Antd)
//
// // 引入vuesax
// import Vuesax from 'vuesax'
// import 'vuesax/dist/vuesax.css' //Vuesax styles
// Vue.use(Vuesax)

// 引入echarts
import echarts from 'echarts'
Vue.prototype.$echarts = echarts


// axios挂载 file系统
import httpRequest from './http/index'
Vue.prototype.httpRequest = httpRequest

// 添加一键复制
import VueClipboard from 'vue-clipboard2';
Vue.use(VueClipboard);




Vue.config.productionTip = false

import App from './App.vue'

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
