<script setup>
import { ref } from 'vue'
import { ElCard,ElMessageBox,ElSelect,ElOption,ElPagination,ElButton,ElTable,ElTableColumn,ElEmpty,ElDrawer,ElForm,ElFormItem,ElUpload,ElInput, ElMessage} from 'element-plus';
import {weatherListService} from '../api/weather.js'
import {useTokenStore} from '../stores/token.js'
//分页条数据模型
const pageNum = ref(1)//当前页
const total = ref(20)//总条数
const pageSize = ref(3)//每页条数
 
//当每页条数发生了变化，调用此函数
const onSizeChange = (size) => {
    pageSize.value = size
    weatherList()
}
//当前页码发生变化，调用此函数
const onCurrentChange = (num) => {
    pageNum.value = num
    weatherList()
}
//投诉列表数据模型
const weather = ref([
    {
        "id":'',
        "temperature":'',
        "climateType":'',
        "ultraviolet":'',
        "apparent":'',
        "humanit":'',
        "wind":'',
        "time":'',
    },
])
//获取列表
const weatherList=async()=>{
    let params={
        pageNum:pageNum.value,
        pageSize:pageSize.value,
    }
    let result=await weatherListService(params);
    total.value=result.data.total;
    weather.value=result.data.items;
}
weatherList();
</script>
<template>
    <el-card class="page-container">
        <template #header>
            <div class="header">
                <span>天气</span>
            </div>
        </template>
        <el-table :data="weather" style="width: 100%">
            <el-table-column label="序号" width="100" type="index"> </el-table-column>
            <el-table-column label="温度" prop="temperature"></el-table-column>
            <el-table-column label="气候类型" prop="climateType"></el-table-column>
            <el-table-column label="紫外线" prop="ultraviolet"></el-table-column>
            <el-table-column label="体感温度" prop="apparent"></el-table-column>
            <el-table-column label="湿度" prop="humanit"></el-table-column>
            <el-table-column label="风向" prop="wind"></el-table-column>
            <el-table-column label="时间" prop="time"></el-table-column>
            <template #empty>
                <el-empty description="没有数据" />
            </template>
        </el-table>
        <!-- 分页条 -->
        <el-pagination v-model:current-page="pageNum" v-model:page-size="pageSize" :page-sizes="[3, 5 ,10, 15]"
            layout="jumper, total, sizes, prev, pager, next" background :total="total" @size-change="onSizeChange"
            @current-change="onCurrentChange" style="margin-top: 20px; justify-content: flex-end" />
    </el-card>
</template>
 
<style lang="scss" scoped>
.page-container {
    min-height: 100%;
    box-sizing: border-box;
 
    .header {
        display: flex;
        align-items: center;
        justify-content: space-between;
    }
}
.avatar-uploader {
    :deep() {
        .avatar {
            width: 178px;
            height: 178px;
            display: block;
        }
 
        .el-upload {
            border: 1px dashed var(--el-border-color);
            border-radius: 6px;
            cursor: pointer;
            position: relative;
            overflow: hidden;
            transition: var(--el-transition-duration-fast);
        }
 
        .el-upload:hover {
            border-color: var(--el-color-primary);
        }
 
        .el-icon.avatar-uploader-icon {
            font-size: 28px;
            color: #8c939d;
            width: 178px;
            height: 178px;
            text-align: center;
        }
    }
}
</style>