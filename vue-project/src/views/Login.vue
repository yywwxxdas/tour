<script setup>
import { ElButton,ElFormItem,ElForm,ElInput,ElLink,ElMessage} from 'element-plus'
import { User, Lock } from '@element-plus/icons-vue'
import { ref } from 'vue'
import TheWelcome from './back/TheWelcome.vue'
const isRegister = ref(false)
//定义数据模型
const registerdata=ref({
  name1:'',
  password1:'',
  repassword1:'',
})
//校验密码的函数
const checkrepwd=(rule,value,callback)=>{
  if(value === ''){
    callback(new Error('请确认输入密码'))
  }else if(value !== registerdata.value.password1){
    callback('两次输入的密码不一致')
  }else{
    callback()
  }
}
//定义表单校验规则
const rules=ref({
  name1:[
    {required:true, message:'请输入账号', trigger:'blur'}
  ],
  password1:[
    {required:true, message:'请输入密码', trigger:'blur'}
  ],
  repassword1:[
    {validator:checkrepwd, trigger:'blur'}
  ]
})
//调用后台接口，完成注册
import {traregisterService,traloginService} from '../api/traveler.js'
const register=async()=>{
  let result= await traregisterService(registerdata.value);
  if(result.code===0){
    ElMessage.success(result.msg? result.msg:'注册成功');
  }else{
    ElMessage.error('账号已被占用')
  }
}
import {useRouter} from 'vue-router'
import {useTokenStore} from '../stores/token.js'
const router=useRouter()
const tokenStore=useTokenStore();//定义得到存储token的对象
//登录接口
const login=async()=>{
  let result=await traloginService(registerdata.value);
  if(result.code===0){
    ElMessage.success(result.msg? result.msg:'登录成功')
    tokenStore.setToken(result.data)//把token存储到pinia中
    //跳转到首页
    router.push('/layout')
  }else{
    ElMessage.error('登录失败')
  }
}
//清空数据模型
const clearregisterdata=()=>{
  registerdata.value={
    name1:'',
    password1:'',
    repassword1:''
  }
}
</script>

<template>
  <div class="greetings">
    <h1>游客服务系统</h1>
    <h3>
      专注品质服务，领航旅游之路
    </h3>
  </div>
  <div style="width: 400px;padding-top:200px;float: right;margin-right: 180px;">
  <!-- 注册表单 -->
  <el-form ref="form" size="large" autocomplete="off" v-if="isRegister" :model="registerdata" :rules="rules">
    <el-form-item>
      <p class="he1">注册</p>
    </el-form-item>
    <el-form-item prop="name1">
      <el-input :prefix-icon="User" placeholder="请输入账号" v-model="registerdata.name1"></el-input>
    </el-form-item>
    <el-form-item prop="password1">
      <el-input :prefix-icon="Lock" type="password" placeholder="请输入密码" v-model="registerdata.password1"></el-input>
    </el-form-item>
    <el-form-item prop="repassword1">
      <el-input :prefix-icon="Lock" type="password" placeholder="请确认输入密码" v-model="registerdata.repassword1"></el-input>
    </el-form-item>
    <!-- 注册按钮 -->
    <el-form-item>
      <el-button class="button" type="primary" auto-insert-space @click="register">注册</el-button>
    </el-form-item>
    <el-form-item class="flex">
      <el-link type="info" :underline="false" @click="isRegister = false;clearregisterdata()">
       <p style="color: aqua;">← 返回</p> 
      </el-link>
    </el-form-item>
    </el-form>
    <!-- 登录表单 -->
    <el-form ref="form" size="large" autocomplete="off" v-else :model="registerdata" :rules="rules">
    <el-form-item>
      <p class="he1">登录</p>
    </el-form-item>
    <el-form-item prop="name1">
      <el-input :prefix-icon="User" placeholder="请输入账号" v-model="registerdata.name1"></el-input>
    </el-form-item>
      <el-form-item prop="password1">
        <el-input name="password" :prefix-icon="Lock" type="password" placeholder="请输入密码" v-model="registerdata.password1"></el-input>
    </el-form-item>
  <!-- 登录按钮 -->
     <el-form-item>
      <el-button class="button" type="primary" auto-insert-space @click="login">登录</el-button>
     </el-form-item>
        <el-form-item class="flex">
    <el-link type="info" :underline="false" @click="isRegister = true;clearregisterdata()">
      <p style="color: aqua;">注册 →</p>
    </el-link>
    </el-form-item>
  </el-form>
  </div>
  <TheWelcome />
</template>

<style scoped>
h1 {
  font-size: 80px;
  color:#fefefe;
  text-shadow:0px 1px 0px #c0c0c0,
	 0px 2px 0px #b0b0b0,
	 0px 3px 0px #a0a0a0,
	 0px 4px 0px #909090,
	 0px 5px 10px rgba(0, 0, 0, .9);
}

h3 {
    font-size: 30px;
    background: linear-gradient(to right, rgba(211, 197, 197, 0.882), rgb(24, 61, 173));
    -webkit-background-clip: text;
    color: transparent;
}

.greetings{
  padding-top: 150px;
  margin-top: 100px;
  text-align: center;
  margin-left: 150px;
  width: 500px;
  height:500px;
  float: left;
}
.he1{
  font-size: 35px;
  color:#fefefe;
  text-shadow:0px 1px 0px #c0c0c0,
	 0px 2px 0px #b0b0b0,
	 0px 3px 0px #a0a0a0,
	 0px 4px 0px #909090,
	 0px 5px 10px rgba(0, 0, 0, .9);
}
.flex{
  width: 100%;
  display: flex;
  justify-content: space-between;
}
.button {
 width: 100%;
}

</style>
