import request from '../untils/request.js'
import {useTokenStore} from '../stores/token.js'
//显示路况列表
export const roadListService=(params)=>{
    return request.get('/road',{params:params})
 }