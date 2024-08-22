import request from '../untils/request.js'
import {useTokenStore} from '../stores/token.js'
//显示景点列表
export const placeListService=(params)=>{
    return request.get('/place',{params:params})
}