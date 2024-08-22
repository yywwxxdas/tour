<script setup>
import {
    Search
} from '@element-plus/icons-vue'
import { ref } from 'vue'
import { ElCard,ElSelect,ElDialog,ElOption,ElPagination,ElButton,ElTable,ElTableColumn,ElEmpty,ElForm,ElFormItem,ElInput} from 'element-plus';
import {performListService} from '../api/perform.js'
import {useTokenStore} from '../stores/token.js'
const type=ref('')
const name=ref('')
//分页条数据模型
const pageNum = ref(1)//当前页
const total = ref(20)//总条数
const pageSize = ref(3)//每页条数
//控制弹窗是否显示
const dialogVisible = ref(false) 
//当每页条数发生了变化，调用此函数
const onSizeChange = (size) => {
    pageSize.value = size
    performList()
}
//当前页码发生变化，调用此函数
const onCurrentChange = (num) => {
    pageNum.value = num
    performList()
}
//投诉列表数据模型
const perform = ref([
    {
        "id":'',
        "name":'',
        "type":'',
        "location":'',
        "date":'',
        "phone":'',
    },
])
//添加表单数据模型
const performModel = ref({
    id:'',
    name:'',
    type:'',
    location:'',
    date:'',
    phone:'',
})
//获取列表
const performList=async()=>{
    let params={
        pageNum:pageNum.value,
        pageSize:pageSize.value,
        name:name.value?name.value:null,
        type:type.value?type.value:null
    }
    let result=await performListService(params);
    total.value=result.data.total;
    perform.value=result.data.items;
}
performList();
//回显
const showDialog = (row) => {
    dialogVisible.value=true;
    performModel.value.name = row.name;
    performModel.value.type = row.type;
    performModel.value.location=row.location;
    performModel.value.date=row.date;
    performModel.value.phone=row.phone;
}
</script>
<template>
    <el-card class="page-container">
        <template #header>
            <div class="header">
                <span>演出</span>
            </div>
        </template>
       <el-form inline>
            <el-form-item label="演出名称:" style="width: 250px;">
                <el-input v-model="name" ></el-input>
            </el-form-item>
            <el-form-item label="演出类型:" style="width: 250px;">
                <el-select placeholder="请选择" v-model="type" >
                    <el-option label="音乐节" value="音乐节"></el-option>
                    <el-option label="舞台剧" value="舞台剧"></el-option>
                    <el-option label="戏曲" value="戏曲"></el-option>
                    <el-option label="话剧" value="话剧"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="performList">搜索</el-button>
                <el-button @click="name='';type=''">重置</el-button>
            </el-form-item>
        </el-form>
        <el-table :data="perform" style="width: 100%">
            <el-table-column label="序号" width="100" type="index"> </el-table-column>
            <el-table-column label="演出名称" prop="name"></el-table-column>
            <el-table-column label="演出类型" prop="type"></el-table-column>
            <el-table-column label="演出地点" prop="location"></el-table-column>
            <el-table-column label="演出时间" prop="date"></el-table-column>
            <el-table-column label="联系电话" prop="phone"></el-table-column>
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
      <el-dialog v-model="dialogVisible" title="演出详请" width="30%">
            <el-form :model="performModel" label-width="100px" style="padding-right: 30px">
                <el-form-item label="演出名称">
                    <el-input v-model="performModel.name" disabled></el-input>
                </el-form-item>
                <el-form-item label="演出类型">
                    <el-input v-model="performModel.type" disabled></el-input>
                </el-form-item>
                <el-form-item label="演出地点">
                    <el-input v-model="performModel.location"  disabled></el-input>
                </el-form-item>
                <el-form-item label="演出时间">
                    <el-input v-model="performModel.date" disabled></el-input>
                </el-form-item>
                <el-form-item label="联系电话">
                    <el-input v-model="performModel.phone" disabled></el-input>
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