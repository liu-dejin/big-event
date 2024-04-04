/*
 * @Author: Lucky-Firstsnow firstsnow1119@163.com
 * @Date: 2024-04-04 00:12:48
 * @LastEditors: Lucky-Firstsnow firstsnow1119@163.com
 * @LastEditTime: 2024-04-04 11:23:04
 * @FilePath: \big-event-vue3\vite.config.js
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import VueDevTools from 'vite-plugin-vue-devtools'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    vue(),
    VueDevTools(),
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  },
  //配置代理
  server: {
    proxy: {
      '/api': {
        target: 'http://localhost:8080', // 后端服务器地址
        changeOrigin: true, // 是否改变请求域名
        rewrite: (path) => path.replace(/^\/api/, '')//将原有请求路径中的api替换为''
      }
    }
  }
})
