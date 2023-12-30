import './assets/main.css'

import {createApp} from 'vue'
import App from './App.vue'
import router from "@/router";
import ElementPlus from "element-plus"

createApp(App)
    .use(router)
    // size 用于设置表单组件的默认尺寸，zIndex 用于设置弹出组件的层级，zIndex 的默认值为 2000
    .use(ElementPlus, {
        size: "small",
        zIndex: 3000
    })
    .mount('#app')






