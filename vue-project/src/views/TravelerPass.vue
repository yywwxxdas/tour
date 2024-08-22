<script setup>
import { ref } from 'vue'
import {useRouter} from 'vue-router'
const router = useRouter();
import { ElMessage,ElMessageBox } from 'element-plus';
import {traPasswordUpdateService} from '../api/traveler.js'
const passwordUpdate = async() => {
    ElMessageBox.confirm(
        '确认是否重置密码',
        '提示',
        {
            confirmButtonText: '确认',
            cancelButtonText: '取消',
            type: 'warning',
        }
    )
        .then(async () => {
            let result = await traPasswordUpdateService(password.value);
            if(result.code===0){
                ElMessage.success(result.msg? result.msg:'修改成功')
            }else{
                ElMessage.error('操作失败')
            }
        })
        .catch(() => {
            //用户点击了取消
            ElMessage({
                type: 'info',
                message: '取消重置',
            })
        })
}
const password = ref({
old_pwd:'',
new_pwd:'',
re_pwd:''
})
const checkrepwd=(rule,value,callback)=>{
  if(value === ''){
    callback(new Error('请确认输入密码'))
  }else if(value !== password.value.new_pwd){
    callback('两次输入的密码不一致')
  }else{
    callback()
  }
}
const rules=ref({
  old_pwd:[
    {required:true, message:'请输入账号', trigger:'blur'}
  ],
  new_pwd:[
    {required:true, message:'请输入密码', trigger:'blur'}
  ],
  re_pwd:[
    {validator:checkrepwd, trigger:'blur'}
  ]
})
const clearData=()=>{
    password.value.old_pwd=''
    password.value.new_pwd=''
    password.value.re_pwd=''
}
</script>
<template>
    <el-card class="page-container">
        <template #header>
            <div class="header">
                <span>重置密码</span>
            </div>
        </template>
        <el-row>
            <el-col :span="12">
                <el-form :model="password" :rules="rules" label-width="100px" size="large">
                    <el-form-item label="原密码" prop="old_pwd">
                        <el-input type="password" placeholder="请输入原密码" show-password v-model="password.old_pwd"></el-input>
                    </el-form-item>
                    <el-form-item label="新密码" prop="new_pwd">
                        <el-input type="password" placeholder="请输入新密码" show-password v-model="password.new_pwd"></el-input>
                    </el-form-item>
                    <el-form-item label="确认新密码" prop="re_pwd">
                        <el-input type="password" placeholder="请输入确认密码" show-password v-model="password.re_pwd"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="passwordUpdate">修改密码</el-button>
                        <el-button type="primary" @click="clearData">重置</el-button>
                    </el-form-item>
                </el-form>
            </el-col>
        </el-row>
    </el-card>
</template>