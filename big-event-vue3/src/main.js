/*
 * @Author: Lucky-Firstsnow firstsnow1119@163.com
 * @Date: 2024-04-04 00:12:48
 * @LastEditors: Lucky-Firstsnow firstsnow1119@163.com
 * @LastEditTime: 2024-04-04 11:11:05
 * @FilePath: \big-event-vue3\src\main.js
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
import './assets/main.scss'

import {createApp} from 'vue'
import {createPinia} from 'pinia'
import {createPersistedState} from 'pinia-persistedstate-plugin'
import ElementPlus, {ElMessage} from 'element-plus'
import 'element-plus/dist/index.css'
import locale from 'element-plus/dist/locale/zh-cn.js'
import App from './App.vue'
import router from './router'
import {useTokenStore} from "@/stores/token.js";
import { QuillEditor } from '@vueup/vue-quill'
import '@vueup/vue-quill/dist/vue-quill.snow.css'

const app = createApp(App)

app.component("quill-editor", QuillEditor)

const pinia = createPinia()
pinia.use(createPersistedState())
app.use(pinia)
app.use(router)
app.use(ElementPlus, {locale})

// 调用useTokenStore得到状态
const tokenStore = useTokenStore();

router.beforeEach((to, from, next) => {
    if (to.name !== 'login' && !tokenStore.token) {
        ElMessage.error('请先登录')
        next({name: 'login'})
    } else {
        next()
    }
})

app.mount('#app')
