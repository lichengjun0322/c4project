import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
import analysic from '../views/analysic.vue';
import yuanqu from '../views/yuanqu.vue'
import yuanquzonglan from "@/views/yuanquzonglan";
import yuanqu1 from "@/views/yuanqu";
import make_oplist from "@/views/make_oplist";
import shipinfenxi from "@/views/shipinfenxi";
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
                path: '/shipinfenxi',
                name: 'shipinfenxi',
                component: shipinfenxi,
                hidden: true,
                props:true
            },
            {
                path: '/tongjirizhi',
                name: 'tongjirizhi',
                component: tongjirizhi,
            },
            {
                path: '/analysic',
                name: 'analysic',
                component: analysic,
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
