import request from '../untils/request.js'
import {useTokenStore} from '../stores/token.js'
//显示天气列表
export const emergencyListService=(params)=>{
    return request.get('/emergency',{params:params})
}