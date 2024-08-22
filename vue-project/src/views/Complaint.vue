<script setup>
import {
    Delete
} from '@element-plus/icons-vue'
import { ref } from 'vue'
import { ElCard,ElInputNumber,ElMessageBox,ElSelect,ElOption,ElPagination,ElButton,ElTable,ElTableColumn,ElEmpty,ElDrawer,ElForm,ElFormItem,ElUpload,ElInput, ElMessage} from 'element-plus';
import {complaintAddService,complaintListService,complaintDeleteService} from '../api/complaint.js'
import {useTokenStore} from '../stores/token.js'
//用户搜索时选中的处理状态
const status=ref('')

//分页条数据模型
const pageNum = ref(1)//当前页
const total = ref(20)//总条数
const pageSize = ref(3)//每页条数
 
//当每页条数发生了变化，调用此函数
const onSizeChange = (size) => {
    pageSize.value = size
    complaintList()
}
//当前页码发生变化，调用此函数
const onCurrentChange = (num) => {
    pageNum.value = num
    complaintList()
}
//投诉列表数据模型
const complaint = ref([
    {
        "id":'',
        "content":'',
        "img":'',
        "comname":'',
        "createtime":'',
        "status":'',
        "evaluate":'',
        "consequence":'',
        "grade":'',
        "handingcreatetime":'',
    },
])
//获取投诉列表
const complaintList=async()=>{
    let params={
        pageNum:pageNum.value,
        pageSize:pageSize.value,
        status:status.value?status.value:null
    }
    let result=await complaintListService(params);
    total.value=result.data.total;
    complaint.value=result.data.items;
}
complaintList();
import {Plus} from '@element-plus/icons-vue'
//控制抽屉是否显示
const visibleDrawer = ref(false)
//添加表单数据模型
const complaintModel = ref({
    evaluate: '',
    grade: '',
    img:'',
    content:'',

})
const tokenStore=useTokenStore();
const uploadSuccess=(result)=>{
    complaintModel.value.img=result.data;

}//发布投诉
const addcomplaint=async()=>{
    let result=await complaintAddService(complaintModel.value);
    ElMessage.success(result.msg?result.msg:'发布成功')
    visibleDrawer.value=false;
    complaintList()

}
//删除投诉
const deleteManage = async(row) => {
    ElMessageBox.confirm(
        '确认是否删除该投诉？',
        '提示',
        {
            confirmButtonText: '确认',
            cancelButtonText: '取消',
            type: 'warning',
        }
    )
        .then(async () => {
            let result = await complaintDeleteService(row.id)
            ElMessage.success(result.msg?result.msg:'删除成功')
            complaintList()
        })
        .catch(() => {
            ElMessage({
                type: 'info',
                message: '取消删除',
            })
        })
}
const handleChange = async(value) => {
  console.log(value)
}
</script>
<template>
    <el-card class="page-container">
        <template #header>
            <div class="header">
                <span>投诉</span>
                <div class="extra">
                    <el-button type="primary" @click="visibleDrawer=true">提交投诉</el-button>
                </div>
            </div>
        </template>
        <el-form inline>
            <el-form-item label="处理状态:" style="width: 250px;">
                <el-select placeholder="请选择" v-model="status" >
                    <el-option label="完成" value="完成"></el-option>
                    <el-option label="未完成" value="未完成"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="complaintList">搜索</el-button>
                <el-button @click="status=''">重置</el-button>
            </el-form-item>
        </el-form>
        <el-table :data="complaint" style="width: 100%">
            <el-table-column label="序号" width="100" type="index"> </el-table-column>
            <el-table-column label="投诉内容" prop="content"></el-table-column>
            <el-table-column label="图片" prop="img"></el-table-column>
            <el-table-column label="账号" prop="comname"></el-table-column>
            <el-table-column label="提交时间" prop="createtime"></el-table-column>
            <el-table-column label="处理状态" prop="status"></el-table-column>
            <el-table-column label="投诉反馈" prop="evaluate"></el-table-column>
            <el-table-column label="处理结果" prop="consequence"></el-table-column>
            <el-table-column label="评价" prop="grade"></el-table-column>
            <el-table-column label="处理时间" prop="handingcreatetime"></el-table-column>
            <el-table-column label="操作" width="100">
                <template #default="{ row }">
                    <el-button :icon="Delete" circle plain type="danger" @click="deleteManage(row)"></el-button>
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
        <!-- 抽屉 -->
        <el-drawer v-model="visibleDrawer" title="提交投诉" direction="rtl" size="50%">
            <!-- 添加投诉表单 -->
            <el-form :model="complaintModel" label-width="100px" >
                <el-form-item label="投诉反馈" >
                    <el-input v-model="complaintModel.evaluate"></el-input>
                </el-form-item>
                <el-form-item label="评价" >
                    <el-input-number v-model="complaintModel.grade" :min="1" :max="10" @change="handleChange" />
                </el-form-item>
                <el-form-item label="投诉图片">
                    <el-upload class="avatar-uploader" 
                    :auto-upload="true" 
                    :show-file-list="false"
                    action="/api/upload"
                    name="file"
                    :headers="{'Authorization':tokenStore.token}"
                    :on-success="uploadSuccess"
                    >
                        <img v-if="complaintModel.img" :src="complaintModel.img" class="avatar" />
                        <el-icon v-else class="avatar-uploader-icon">
                            <Plus />
                        </el-icon>
                    </el-upload>
                </el-form-item>
                <el-form-item label="投诉内容" >
                    <el-input v-model="complaintModel.content" type="textarea" :rows="3"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="addcomplaint">发布</el-button>
                </el-form-item>
            </el-form>
        </el-drawer>
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