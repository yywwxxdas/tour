<script setup>
import {
    Search
} from '@element-plus/icons-vue'
import { ref } from 'vue'
import { ElCard,ElDialog,ElSelect,ElOption,ElPagination,ElButton,ElTable,ElTableColumn,ElEmpty,ElForm,ElFormItem,ElInput} from 'element-plus';
import {roadListService} from '../api/road.js'
import {useTokenStore} from '../stores/token.js'
const status=ref('')
const location=ref('')
//分页条数据模型
const pageNum = ref(1)//当前页
const total = ref(20)//总条数
const pageSize = ref(3)//每页条数
 //控制弹窗是否显示
const dialogVisible = ref(false)
//当每页条数发生了变化，调用此函数
const onSizeChange = (size) => {
    pageSize.value = size
    roadList()
}
//当前页码发生变化，调用此函数
const onCurrentChange = (num) => {
    pageNum.value = num
    roadList()
}
//路况列表数据模型
const road = ref([
    {
        "id":'',
        "location":'',
        "status":'',
        "description":'',
    },
])
//添加表单数据模型
const roadModel = ref({
    id:'',
    location:'',
    status:'',
    description:'',

})
//获取列表
const roadList=async()=>{
    let params={
        pageNum:pageNum.value,
        pageSize:pageSize.value,
        location:location.value?location.value:null,
        status:status.value?status.value:null
    }
    let result=await roadListService(params);
    total.value=result.data.total;
    road.value=result.data.items;
}
roadList();
//路况回显
const showDialog = (row) => {
    dialogVisible.value=true;
    roadModel.value.location = row.location;
    roadModel.value.status = row.status;
    roadModel.value.description = row.description;
}
</script>
<template>
    <el-card class="page-container">
        <template #header>
            <div class="header">
                <span>路况</span>
            </div>
        </template>
       <el-form inline>
            <el-form-item label="道路地址:" style="width: 250px;">
                <el-input v-model="location" ></el-input>
            </el-form-item>
            <el-form-item label="路况类型:" style="width: 250px;">
                <el-select placeholder="请选择" v-model="status" >
                    <el-option label="通畅" value="通畅"></el-option>
                    <el-option label="正常" value="正常"></el-option>
                    <el-option label="拥挤" value="拥挤"></el-option>
                    <el-option label="很拥挤" value="很拥挤"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="roadList">搜索</el-button>
                <el-button @click="status='';location=''">重置</el-button>
            </el-form-item>
        </el-form>
        <el-table :data="road" style="width: 100%">
            <el-table-column label="序号" width="100" type="index"> </el-table-column>
            <el-table-column label="路况地址" prop="location"></el-table-column>
            <el-table-column label="路况类型" prop="status"></el-table-column>
            <el-table-column label="路况描述" prop="description"></el-table-column>
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
    <el-dialog v-model="dialogVisible" title="路况详请" width="30%">
            <el-form :model="roadModel" label-width="100px" style="padding-right: 30px">
                <el-form-item label="路况地址">
                    <el-input v-model="roadModel.location" disabled></el-input>
                </el-form-item>
                <el-form-item label="路况类型">
                    <el-input v-model="roadModel.status" disabled></el-input>
                </el-form-item>
                <el-form-item label="路况描述">
                    <el-input v-model="roadModel.description" type="textarea" disabled></el-input>
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