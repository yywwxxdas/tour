<script setup>
import {CaretBottom,Guide,House,UserFilled,Service,Bicycle,Sunny,Warning,VideoCameraFilled,User,SwitchButton,WarnTriangleFilled,OfficeBuilding,School,Place,GoodsFilled,KnifeFork,Football} from '@element-plus/icons-vue'
import {ElContainer,ElHeader,ElMain,ElFooter,ElDropdown,ElDropdownItem,ElDropdownMenu,ElAvatar,ElAside,ElMenu,ElMenuItem,ElSubMenu, ElMessageBox, ElMessage} from 'element-plus'
//获取游客信息
import {travelerInfoService} from '../api/traveler.js'
import useTraInfostore from '../stores/trainfo.js'
import avatar from '../assets/aa.png'
import { useTokenStore } from '../stores/token.js'
const tokenStore=useTokenStore();
const traInfostore=useTraInfostore();
const gettraInfo=async()=>{
  let result=await travelerInfoService();
  traInfostore.setInfo(result.data);
}
gettraInfo();
//条目点击
import {useRouter} from 'vue-router'
const router=useRouter();
const headCommand=(command)=>{
  if(command==='logout'){
    ElMessageBox.confirm(
    '你确认要退出吗？',
    '提示',
    {
      confirmButtonText:'确认',
      cancelButtonText:'取消',
      type:'warning'
    }
  )
    .then(async()=>{
      //清空pinia中存储的信息
      tokenStore.removeToken()
      traInfostore.removeInfo()
      router.push('/')
      ElMessage({
        type:'success',
        message:'退出成功'
      })
    })
    .catch(()=>{
      ElMessage({
        type:'info',
        message:'取消退出'
      })
    })
  }else{
    router.push('/traveler/'+command)
  }
}
</script>
<template>
  <el-container class="layout-container">
        <!-- 左侧菜单 -->
        <el-aside width="200px">
            <div class="el-aside__logo"></div>
            <el-menu active-text-color="#18CBD3" background-color="#232323"  text-color="#fff"
                router>
                <el-menu-item index="/house">
                  <el-icon><House /></el-icon>
                    <span>主页</span>
                </el-menu-item>
                <el-sub-menu index="0">
                    <template #title>
                      <el-icon><Place /></el-icon>
                        <span>景点</span>
                    </template>
                    <el-menu-item index="/place">
                      <el-icon><Place /></el-icon>
                        <span>旅游景区</span>
                    </el-menu-item>
                    <el-menu-item index="/map">
                      <el-icon><Guide /></el-icon>
                        <span>导航地图</span>
                    </el-menu-item>
                </el-sub-menu>
                <el-menu-item index="/emer">
                  <el-icon><WarnTriangleFilled /></el-icon>
                    <span>旅游应急信息</span>
                </el-menu-item>
                <el-menu-item index="/hotel">
                  <el-icon><OfficeBuilding /></el-icon>
                    <span>酒店</span>
                </el-menu-item>
                <el-sub-menu index="6">
                    <template #title>
                      <el-icon><GoodsFilled /></el-icon>
                        <span>餐饮娱乐</span>
                    </template>
                    <el-menu-item index="/pla/food">
                      <el-icon><KnifeFork /></el-icon>
                        <span>餐饮</span>
                    </el-menu-item>
                    <el-menu-item index="/pla/play">
                      <el-icon><Football /></el-icon>
                        <span>娱乐</span>
                    </el-menu-item>
                    <el-menu-item index="/pla/perform">
                      <el-icon><VideoCameraFilled /></el-icon>
                        <span>演出团体</span>
                    </el-menu-item>
                </el-sub-menu>
                <el-sub-menu index="10">
                    <template #title>
                      <el-icon><Warning /></el-icon>
                        <span>天气及路况</span>
                    </template>
                    <el-menu-item index="/warn/weather">
                      <el-icon><Sunny /></el-icon>
                        <span>天气</span>
                    </el-menu-item>
                    <el-menu-item index="/warn/road">
                      <el-icon><Bicycle /></el-icon>
                        <span>路况</span>
                    </el-menu-item>
                </el-sub-menu>
                <el-menu-item index="/complaint">
                  <el-icon><Service /></el-icon>
                    <span>投诉</span>
                </el-menu-item>
                <el-sub-menu index="14">
                    <template #title>
                      <el-icon><UserFilled /></el-icon>
                        <span>个人中心</span>
                    </template>
                    <el-menu-item index="/traveler/info">
                      <el-icon><UserFilled /></el-icon>
                        <span>基本信息</span>
                    </el-menu-item>
                    <el-menu-item index="/traveler/avatar">
                      <el-icon><UserFilled /></el-icon>
                        <span>修改头像</span>
                    </el-menu-item>
                    <el-menu-item index="/traveler/password">
                      <el-icon><UserFilled /></el-icon>
                        <span>修改密码</span>
                    </el-menu-item>
                </el-sub-menu>
            </el-menu>
        </el-aside>
        <!-- 右侧主区域 -->
        <el-container>
            <!-- 头部区域 -->
            <el-header>
                <div>用户：<strong>{{traInfostore.info.name}}</strong></div>
                <el-dropdown placement="bottom-end" @command="headCommand">
                    <span class="el-dropdown__box">
                        <el-avatar :src="traInfostore.info.image?traInfostore.info.image:avatar" />
                        <el-icon>
                            <CaretBottom />
                        </el-icon>
                    </span>
                    <template #dropdown>
                        <el-dropdown-menu>
                            <el-dropdown-item command="info" :icon="User">基本资料</el-dropdown-item>
                            <el-dropdown-item command="logout" :icon="SwitchButton">退出登录</el-dropdown-item>
                        </el-dropdown-menu>
                    </template>
                </el-dropdown>
            </el-header>
            <!-- 中间区域 -->
            <el-main style="padding-left: 10px;padding-right: 0%;">
                <router-view></router-view>
            </el-main>
            <!-- 底部区域 -->
            <el-footer></el-footer>
        </el-container>
    </el-container>
</template>
<style scoped>
.layout-container {
    height: 100vh;
    .el-aside {
        background-color: #232323;
        &__logo {
            background: url('../assets/travel.png') no-repeat center / 120px auto;
            height: 150px;
        }
        .el-menu {
            border-right: none;
        }
    }
 
    .el-header {
        background-color: #fff;
        display: flex;
        align-items: center;
        justify-content: space-between;
 
        .el-dropdown__box {
            display: flex;
            align-items: center;
 
            .el-icon {
                color: #999;
                margin-left: 10px;
            }
 
            &:active,
            &:focus {
                outline: none;
            }
        }
    }
 
    .el-footer {
        display: flex;
        align-items: center;
        justify-content: center;
        font-size: 14px;
        color: #666;
    }
}
.main {
  position: absolute;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  width: 100%;
}
</style>