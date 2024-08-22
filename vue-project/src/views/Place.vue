<script setup>
import {
    Search
} from '@element-plus/icons-vue'
import { ref } from 'vue'
import { ElCard,ElDialog,ElSelect,ElOption,ElPagination,ElButton,ElTable,ElTableColumn,ElEmpty,ElForm,ElFormItem,ElInput} from 'element-plus';
import {placeListService} from '../api/place.js'
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
    placeList()
}
//当前页码发生变化，调用此函数
const onCurrentChange = (num) => {
    pageNum.value = num
    placeList()
}
//列表数据模型
const place = ref([
    {
        "id":'',
        "name":'',
        "introduction":'',
        "type":'',
        "price":'',
        "img":'',
    },
])
//添加表单数据模型
const placeModel = ref({
    id:'',
    name:'',
    introduction:'',
    type:'',
    price:'',
    img:'',

})
//获取列表
const placeList=async()=>{
    let params={
        pageNum:pageNum.value,
        pageSize:pageSize.value,
        name:name.value?name.value:null,
        type:type.value?type.value:null
    }
    let result=await placeListService(params);
    total.value=result.data.total;
    place.value=result.data.items;
}
placeList();
//回显
const showDialog = (row) => {
    dialogVisible.value=true;
    placeModel.value.name = row.name;
    placeModel.value.introduction = row.introduction;
    placeModel.value.type=row.type;
    placeModel.value.price=row.price;
    placeModel.value.img=row.img;
}
</script>
<template>
    <el-card class="page-container">
        <template #header>
            <div class="header">
                <span>景点</span>
            </div>
        </template>
       <el-form inline>
            <el-form-item label="景点名称:" style="width: 250px;">
                <el-input v-model="name" ></el-input>
            </el-form-item>
            <el-form-item label="景点类型:" style="width: 250px;">
                <el-select placeholder="请选择" v-model="type" >
                    <el-option label="自然景观" value="自然景观"></el-option>
                    <el-option label="名胜古迹" value="名胜古迹"></el-option>
                    <el-option label="博物馆" value="博物馆"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="placeList">搜索</el-button>
                <el-button @click="name='';type=''">重置</el-button>
            </el-form-item>
        </el-form>
        <el-table :data="place" style="width: 100%">
            <el-table-column label="序号" width="100" type="index"> </el-table-column>
            <el-table-column label="景点名称" prop="name"></el-table-column>
            <el-table-column label="景点介绍" prop="introduction"></el-table-column>
            <el-table-column label="景点类型" prop="type"></el-table-column>
            <el-table-column label="门票价格" prop="price"></el-table-column>
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
     <el-dialog v-model="dialogVisible" title="景点详请" width="30%">
            <el-form :model="placeModel" label-width="100px" style="padding-right: 30px">
                <el-form-item label="景点名称">
                    <el-input v-model="placeModel.name" disabled></el-input>
                </el-form-item>
                <el-form-item label="景点介绍">
                    <el-input v-model="placeModel.introduction" type="textarea" disabled></el-input>
                </el-form-item>
                <el-form-item label="景点类型">
                    <el-input v-model="placeModel.type" disabled></el-input>
                </el-form-item>
                <el-form-item label="门票价格">
                    <el-input v-model="placeModel.price" disabled></el-input>
                </el-form-item>
                <el-form-item label="图片">
                    <el-input v-model="placeModel.img" disabled></el-input>
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