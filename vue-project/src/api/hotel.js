import request from '../untils/request.js'
import {useTokenStore} from '../stores/token.js'
//显示酒店列表
export const hotelListService=(params)=>{
    return request.get('/hotel',{params:params})
}