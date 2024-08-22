import './assets/main.css'
import { createApp } from 'vue'
// 粒子特效插件  particles.vue3
import Particles from 'particles.vue3'
//element组件
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import App from './App.vue'
import router from './router/router'
//pinia状态管理库
import {createPinia} from 'pinia'
//pinia持久化插件
import {createPersistedState} from 'pinia-persistedstate-plugin'
//element组件中文包
import locale from 'element-plus/dist/locale/zh-cn.js'
//导入百度地图的插件
import BaiduMap from 'vue-baidu-map-3x'
const app = createApp(App)
const pinia=createPinia()
const persist=createPersistedState();
app.use(pinia)
pinia.use(persist)
//页面挂载粒子插件
app.use(Particles);
app.use(ElementPlus,{locale});
app.use(router);
app.mount('#app')
app.use(BaiduMap, {
    ak: 'QMkTp37BYOV2PqIdasNqKrX4YPr2gJ4Q'  //这个地方是官方提供的ak密钥
  })
  

