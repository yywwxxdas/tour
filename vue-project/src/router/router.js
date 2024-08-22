import {createRouter,createWebHistory} from 'vue-router'
import HelloWorld from '../views/Login.vue'
import Layout1 from '../views/Layout1.vue'
import UserInfo from '../views/TravelerInfo.vue'
import Userpwd from '../views/TravelerPass.vue'
import UserAva from '../views/TravelerAva.vue'
import Complaint from '../views/Complaint.vue'
import House from '../views/House.vue'
import Road from '../views/Road.vue'
import Weather from '../views/Weather.vue' 
import Food from '../views/food.vue'
import Play from '../views/Play.vue'
import Perform from '../views/Perform.vue'
import Place from '../views/Place.vue'
import Emergency from '../views/Emergency.vue'
import Hotel from '../views/Hotel.vue'
import map from '../views/map.vue'
//定义路由关系
//redirect重定向路由
const routes=[
    {path:'/',component:HelloWorld},
    {path:'/layout',component:Layout1,redirect:'/house',children:[
        {path:'/traveler/info',component:UserInfo},
        {path:'/traveler/avatar',component:UserAva},
        {path:'/traveler/password',component:Userpwd},
        {path:'/complaint',component:Complaint},
        {path:'/house',component:House},
        {path:'/warn/weather',component:Weather},
        {path:'/warn/road',component:Road},
        {path:'/pla/food',component:Food},
        {path:'/pla/play',component:Play},
        {path:'/pla/perform',component:Perform},
        {path:'/place',component:Place},
        {path:'/emer',component:Emergency},
        {path:'/hotel',component:Hotel},
        {path:'/map',component:map}
    ]}
]
//创建路由器
const router=createRouter({
    history:createWebHistory(),
    routes:routes
})
//导出路由
export default router