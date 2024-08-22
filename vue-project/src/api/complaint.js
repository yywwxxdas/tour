import request from '../untils/request.js'
import {useTokenStore} from '../stores/token.js'
//显示投诉列表
export const complaintListService=(params)=>{
   return request.get('/complaint',{params:params})
}
//提交投诉
export const complaintAddService = (complaintModel)=>{
   return request.post('/complaint',complaintModel);
}
//删除投诉
export const complaintDeleteService = (id) => {
   return request.delete('/complaint?id='+id)
}