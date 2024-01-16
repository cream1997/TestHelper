import './assets/main.css'

import {createApp} from 'vue'
import App from './App.vue'
import router from "@/router";
import {Tip} from "@/tools/CommonTools"
import ElementPlus from "element-plus"
import 'element-plus/dist/index.css'
import {createPinia} from "pinia";

const app = createApp(App)
    .use(router)
    // size 用于设置表单组件的默认尺寸，zIndex 用于设置弹出组件的层级，zIndex 的默认值为 2000
    .use(ElementPlus, {
        size: "small",
        zIndex: 2000
    })
    .use(createPinia()); //引入状态管理库pinia

app.config.errorHandler = (err: any, vm, info) => {
    Tip.error(err.message);
}
// 挂载
app.mount('#app')






