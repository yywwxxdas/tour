import request from '../untils/request.js'
import {useTokenStore} from '../stores/token.js'
//显示演出列表
export const performListService=(params)=>{
    return request.get('/perform',{params:params})
}