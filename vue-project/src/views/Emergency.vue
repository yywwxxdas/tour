<script setup>
import {
    Search
} from '@element-plus/icons-vue'
import { ref } from 'vue'
import { ElCard,ElDialog,ElPagination,ElButton,ElTable,ElTableColumn,ElEmpty,ElDrawer,ElForm,ElFormItem,ElUpload,ElInput, ElMessage} from 'element-plus';
import {emergencyListService} from '../api/emergency.js'
import {useTokenStore} from '../stores/token.js'
//分页条数据模型
const pageNum = ref(1)//当前页
const total = ref(20)//总条数
const pageSize = ref(3)//每页条数
 //控制弹窗是否显示
const dialogVisible = ref(false)
//当每页条数发生了变化，调用此函数
const onSizeChange = (size) => {
    pageSize.value = size
    emergencyList()
}
//当前页码发生变化，调用此函数
const onCurrentChange = (num) => {
    pageNum.value = num
    emergencyList()
}
//列表数据模型
const emergency = ref([
    {
        "id":'',
        "title":'',
        "content":'',
        "state":'',
        "createtime":'',
        "updatetime":'',
        "img":'',
    },
])
//添加表单数据模型
const emergencyModel = ref({
    id:'',
    title:'',
    content:'',
    state:'',
    createtime:'',
    updatetime:'',
    img:'',
})
//获取列表
const emergencyList=async()=>{
    let params={
        pageNum:pageNum.value,
        pageSize:pageSize.value,
    }
    let result=await emergencyListService(params);
    total.value=result.data.total;
    emergency.value=result.data.items;
}
emergencyList();
//回显
const showDialog = (row) => {
    dialogVisible.value=true;
    emergencyModel.value.title = row.title;
    emergencyModel.value.content = row.content;
    emergencyModel.value.state = row.state;
    emergencyModel.value.createtime=row.createtime;
    emergencyModel.value.updatetime=row.updatetime;
    emergencyModel.value.img=row.img;
}
</script>
<template>
    <el-card class="page-container">
        <template #header>
            <div class="header">
                <span>应急信息</span>
            </div>
        </template>
        <el-table :data="emergency" style="width: 100%">
            <el-table-column label="序号" width="100" type="index"> </el-table-column>
            <el-table-column label="应急信息标题" prop="title"></el-table-column>
            <el-table-column label="应急信息内容" prop="content"></el-table-column>
            <el-table-column label="状态" prop="state"></el-table-column>
            <el-table-column label="发布时间" prop="createtime"></el-table-column>
            <el-table-column label="更新时间" prop="updatetime"></el-table-column>
            <el-table-column label="图片" prop="img"></el-table-column>
            <el-table-column label="操作" width="100">
                <template #default="{row}">
                    <el-button :icon="Search" circle plain @click="showDialog(row)"></el-button>
                </template>
            </el-table-column>
            <template #empty>
                <el-empty description="没有数据" />
            </template>
        </el-table>
        <!-- 分页条 -->
        <el-pagination v-model:current-page="pageNum" v-model:page-size="pageSize" :page-sizes="[3, 5 ,10, 15]"
            layout="jumper, total, sizes, prev, pager, next" background :total="total" @size-change="onSizeChange"
            @current-change="onCurrentChange" style="margin-top: 20px; justify-content: flex-end" />
    </el-card>
     <!-- 弹窗 -->
     <el-dialog v-model="dialogVisible" title="应急信息详请" width="30%">
            <el-form :model="emergencyModel" label-width="100px" style="padding-right: 30px">
                <el-form-item label="标题">
                    <el-input v-model="emergencyModel.title" disabled></el-input>
                </el-form-item>
                <el-form-item label="内容">
                    <el-input v-model="emergencyModel.content" type="textarea" disabled></el-input>
                </el-form-item>
                <el-form-item label="状态">
                    <el-input v-model="emergencyModel.state" disabled></el-input>
                </el-form-item>
                <el-form-item label="发布时间">
                    <el-input v-model="emergencyModel.createtime" disabled></el-input>
                </el-form-item>
                <el-form-item label="更新时间">
                    <el-input v-model="emergencyModel.updatetime" disabled></el-input>
                </el-form-item>
                <el-form-item label="图片">
                    <el-input v-model="emergencyModel.img" disabled></el-input>
                </el-form-item>
            </el-form>
    </el-dialog>
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