import request from '../untils/request.js'
import {useTokenStore} from '../stores/token.js'
//显示天气列表
export const weatherListService=(params)=>{
    return request.get('/climate',{params:params})
 }