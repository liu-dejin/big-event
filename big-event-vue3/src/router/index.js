import {createRouter, createWebHashHistory} from 'vue-router'
import Login from '@/views/Login.vue'
import Layout from "@/views/Layout.vue";
import ArticleCategory from "@/views/article/ArticleCategory.vue";
import ArticleManage from "@/views/article/ArticleManage.vue";
import UserInfo from "@/views/user/UserInfo.vue";
import UserResetPassword from "@/views/user/UserResetPassword.vue";
import UserAvatar from "@/views/user/UserAvatar.vue";

const routes = [
    {
        path: '/',
        component: Layout,
        redirect: '/article/manage',
        children: [
            {path: '/article/category', component: ArticleCategory},
            {path: '/article/manage', component: ArticleManage},
            {path: '/user/info', component: UserInfo},
            {path: '/user/avatar', component: UserAvatar},
            {path: '/user/password', component: UserResetPassword},
        ]
    },
    {
        path: '/login',
        name: 'login',
        // route level code-splitting
        // this generates a separate chunk (About.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: Login
    }
]

const router = createRouter({
    history: createWebHashHistory(import.meta.env.BASE_URL),
    routes
})

export default router
