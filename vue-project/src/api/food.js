import request from '../untils/request.js'
import {useTokenStore} from '../stores/token.js'
//显示餐饮列表
export const foodListService=(params)=>{
    return request.get('/canyinyvle/food',{params:params})
}
//显示娱乐列表
export const playListService=(params)=>{
    return request.get('/canyinyvle/play',{params:params})
}