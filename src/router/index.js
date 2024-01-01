import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/authViews/Login'
import Main from '../views/Main'
import SystemIndex from "@/views/SystemIndex";
import {Message} from 'element-ui'


import order_manage from "@/views/order/manage"
import product_manage from "@/views/product/manage"
import productCategory_manage from "@/views/productCategory/manage"
import goods_manage from "@/views/goods/manage"
import sysDictList from "@/views/sys/SysDictList.vue";

// 自己定义的请求包。只能在组件中使用，如果非要在这里使用，必须引入

Vue.use(VueRouter)


const routes = [

    // 登录相关路由-单独的
    {
        path: '/',
        name: 'login',
        meta: {
            // 已经登录 如果这里为true 那么就不在登录
            isExist: true
        },
        component: Login
    },


    // 首页内嵌路由
    {
        path: '/main',
        name: 'main',
        component: Main,
        children: [
            {
                path: '/index',
                name: 'systemIndex',
                meta: {
                    isLogin: true
                },
                component: SystemIndex
            },

            {
                path: '/productCategory/manage',
                name: 'productCategory',
                meta: {
                    isLogin: true
                },
                component: productCategory_manage
            },

            {
                path: '/product/manage',
                name: 'product',
                meta: {
                    isLogin: true
                },
                component: product_manage
            },

            {
                path: '/goods/manage',
                name: 'goods',
                meta: {
                    isLogin: true
                },
                component: goods_manage
            },

            {
                path: '/order/manage',
                name: 'order',
                meta: {
                    isLogin: true
                },
                component: order_manage
            },

            {
                path: '/sys/sysDict',
                name: 'order',
                meta: {
                    isLogin: true
                },
                component: sysDictList
            },
        ]
    },
]

const router = new VueRouter({
    routes
})

//全局前置守卫：初始化时执行、每次路由切换前执行
router.beforeEach((to, from, next) => {

    if (to.meta.isExist) {
        if (localStorage.getItem("token") == null) {
            next()
        } else {
            next({
                path: '/index'
            })
        }
    }


    if (to.meta.isLogin) { //判断当前路由是否需要进行权限控制
        // console.log(store.state.user)
        if (localStorage.getItem("token") == null) {
            Message.info("请先登录")
            next({
                path: '/',
            })
        }
        next()
    } else {
        next() //放行
    }
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
