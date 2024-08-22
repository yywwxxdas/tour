<script setup>
import { ref } from 'vue'
import useTraInfostore from '../stores/trainfo.js'
import { ElButton,ElFormItem,ElForm,ElInput,ElCard,ElRow,ElCol,ElMessage,ElMessageBox} from 'element-plus'
import {traInfoUpdateService} from '../api/traveler.js'
const traInfostore=useTraInfostore();
const traInfo = ref({...traInfostore.info})
//修改信息
const updateTraInfo=async()=>{
  ElMessageBox.confirm(
    '确认是否修改信息',
    '提示',
    {
      confirmButtonText: '确认',
      cancelButtonText: '取消',
      type: 'warning',
    }
    )
    .then(async () => {
      let result=await traInfoUpdateService(traInfo.value);
      if(result.code===0){
        ElMessage.success(result.msg?result.msg:'修改成功');
        traInfostore.setInfo(traInfo.value)
      }else{
        ElMessage.error('操作失败')
      }
      })
      .catch(() => {
        //用户点击了取消
        ElMessage({
        type: 'info',
        message: '取消修改',
      })
    })
}
</script>
<template>
     <el-card class="page-container">
        <template #header>
            <div>
                <span>基本资料</span>
            </div>
        </template>
        <el-row>
            <el-col :span="12" style="padding-left: 100px;">
                <el-form :model="traInfo" label-width="100px" size="large">
                    <el-form-item label="账号">
                        <el-input v-model="traInfo.traname" disabled></el-input>
                    </el-form-item>
                    <el-form-item label="用户名">
                        <el-input v-model="traInfo.name" ></el-input>
                    </el-form-item>
                    <el-form-item label="游客性别">
                        <el-input v-model="traInfo.sex" ></el-input>
                    </el-form-item>
                    <el-form-item label="游客电话">
                        <el-input v-model="traInfo.phone" ></el-input>
                    </el-form-item>
                    <el-form-item label="游客身份证号">
                        <el-input v-model="traInfo.idcard" ></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="updateTraInfo">修改信息</el-button>
                    </el-form-item>
                </el-form>
            </el-col>
        </el-row>
    </el-card>
</template>
<style scoped>
.page-container{
    width: 1200px;
}


</style>