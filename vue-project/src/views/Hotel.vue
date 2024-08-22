<script setup>
import {
    Search
} from '@element-plus/icons-vue'
import { ref } from 'vue'
import { ElCard,ElDialog,ElSelect,ElOption,ElPagination,ElButton,ElTable,ElTableColumn,ElEmpty,ElForm,ElFormItem,ElInput} from 'element-plus';
import {hotelListService} from '../api/hotel.js'
import {useTokenStore} from '../stores/token.js'
const name=ref('')
const type=ref('')
//分页条数据模型
const pageNum = ref(1)//当前页
const total = ref(20)//总条数
const pageSize = ref(3)//每页条数
//控制弹窗是否显示
const dialogVisible = ref(false)
//当每页条数发生了变化，调用此函数
const onSizeChange = (size) => {
    pageSize.value = size
    hotelList()
}
//当前页码发生变化，调用此函数
const onCurrentChange = (num) => {
    pageNum.value = num
    hotelList()
}
//投诉列表数据模型
const hotel = ref([
    {
        "id":'',
        "name":'',
        "image":'',
        "type":'',
        "start":'',
        "address":'',
        "information":'',

    },
])
//添加表单数据模型
const hotelModel = ref({
    id:'',
    name:'',
    image:'',
    type:'',
    start:'',
    address:'',
    information:'',

})
//获取列表
const hotelList=async()=>{
    let params={
        pageNum:pageNum.value,
        pageSize:pageSize.value,
        name:name.value?name.value:null,
        type:type.value?type.value:null
    }
    let result=await hotelListService(params);
    total.value=result.data.total;
    hotel.value=result.data.items;
}
hotelList();
//回显
const showDialog = (row) => {
    dialogVisible.value=true;
    hotelModel.value.name = row.name;
    hotelModel.value.image = row.image;
    hotelModel.value.start = row.start;
    hotelModel.value.address=row.address;
    hotelModel.value.information=row.information;
}
</script>
<template>
    <el-card class="page-container">
        <template #header>
            <div class="header">
                <span>酒店</span>
            </div>
        </template>
       <el-form inline>
            <el-form-item label="酒店名称:" style="width: 250px;">
                <el-input v-model="name" ></el-input>
            </el-form-item>
            <el-form-item label="酒店类型:" style="width: 250px;">
                <el-select placeholder="请选择" v-model="type" >
                    <el-option label="非星级" value="1"></el-option>
                    <el-option label="星级" value="2"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="hotelList">搜索</el-button>
                <el-button @click="name='';type=''">重置</el-button>
            </el-form-item>
        </el-form>
        <el-table :data="hotel" style="width: 100%">
            <el-table-column label="序号" width="100" type="index"> </el-table-column>
            <el-table-column label="名称" prop="name"></el-table-column>
            <el-table-column label="图片" prop="image"></el-table-column>
            <el-table-column label="星级" prop="start"></el-table-column>
            <el-table-column label="地址" prop="address"></el-table-column>
            <el-table-column label="信息" prop="information"></el-table-column>
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
     <el-dialog v-model="dialogVisible" title="酒店详请" width="30%">
            <el-form :model="hotelModel" label-width="100px" style="padding-right: 30px">
                <el-form-item label="名称">
                    <el-input v-model="hotelModel.name" disabled></el-input>
                </el-form-item>
                <el-form-item label="图片">
                    <el-input v-model="hotelModel.image" disabled></el-input>
                </el-form-item>
                <el-form-item label="星级">
                    <el-input v-model="hotelModel.start" disabled></el-input>
                </el-form-item>
                <el-form-item label="地址">
                    <el-input v-model="hotelModel.address" disabled></el-input>
                </el-form-item>
                <el-form-item label="信息">
                    <el-input v-model="hotelModel.information" type="textarea" disabled></el-input>
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