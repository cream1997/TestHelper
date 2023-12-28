import {createRouter, createWebHashHistory} from "vue-router";
import Login from "@/pages/Login.vue";
import Home from "@/pages/Home.vue";

export default createRouter({
    history: createWebHashHistory(), // 路径中带有#号，不需要服务端配合
    routes: [
        {path: "/login", component: Login},
        {path: "/home", component: Home}
    ]
});