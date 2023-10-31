import Vue from 'vue'
import VueRouter from 'vue-router'

import MainPanel from "@/views/MainPanel.vue";
import ShopView from "@/views/pages/ShopView.vue";
import OrderView from "@/views/pages/OrderView.vue";

// 自己定义的请求包。只能在组件中使用，如果非要在这里使用，必须引入

Vue.use(VueRouter)


const routes = [
    // 端口文档路由
    // 首页内嵌路由
    {
        path: '/',
        name: 'MainPanel',
        component: MainPanel,
        children: [
            {
                path: '/shop',
                name: 'ShopView',
                component: ShopView
            },
            {
                path: '/order',
                name: 'OrderView',
                component: OrderView
            },


        ]
    }
]

const router = new VueRouter({
    routes
})

//全局前置守卫：初始化时执行、每次路由切换前执行
router.beforeEach((to, from, next) => {
    next()
})

// //全局后置守卫：初始化时执行、每次路由切换后执行
// router.afterEach((to,from)=>{
//   console.log('afterEach',to,from)
//   if(to.meta.title){
//     document.title = to.meta.title //修改网页的title
//   }else{
//     document.title = 'vue_test'
//   }
// })

/* 解决导航重复问题 */
const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
    return originalPush.call(this, location).catch(err => err)
}


export default router