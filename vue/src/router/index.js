import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
import showdb from '../views/showdatabase.vue'
import yuanqu from '../views/yuanqu.vue'
import n3 from '../views/n3.vue'
import yuanquzonglan from "@/views/yuanquzonglan";
import yuanqu1 from "@/views/yuanqu";
import make_oplist from "@/views/make_oplist";
import jianceguanli from "@/views/jianceguanli";
import shouye from "@/views/shouye";
import store from "@/store";
import login from "@/views/Login";
import register from "@/views/Register";
import tongjirizhi from "@/views/tongjirizhi";




Vue.use(VueRouter)

let admin;
const routes = [
    {
        path:'/',
        name:"login",
        component: login
    },
    {
        path:'/register',
        name:"register",
        component: register
    },
    {
        path: '/shouye',
        name: 'home',
        component: HomeView,
        redirect:"/shouye",
        children: [
            {
                path: '/shouye',
                name: 'shouye',
                component: shouye
            },
            {
                path: '/showdb',
                name: 'showdb',
                component: showdb
            },
            {
                path: '/yuanquzonglan',
                name: 'yuanquzonglan',
                component: yuanquzonglan
            },
            {
                path: '/make_oplist',
                name: 'make_oplist',
                component: make_oplist,
            },
            {
                path: '/jifang',
                name: 'yuanqu',
                component: yuanqu,
            },
            {
                path: '/jianceguanli',
                name: 'jianceguanli',
                component: jianceguanli,
            },
            {
                path: '/tongjirizhi',
                name: 'tongjirizhi',
                component: tongjirizhi,
            }
        ]
    },
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

router.beforeEach((to, from, next) => {
    localStorage.setItem("currentPathName", to.name)  // 设置当前的路由名称，为了在Header组件中去使用
    store.commit("setPath")  // 触发store的数据更新
    next()  // 放行路由
})

export default router
