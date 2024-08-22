import {defineStore} from 'pinia'
import {ref} from 'vue'
//存储登录成功的token数据
export const useTokenStore=defineStore('token',()=>{
    //定义token
    const token=ref('')
    //修改token的值
    const setToken=(newToken)=>{
        token.value=newToken
    }
    //移除token的值
    const removeToken=()=>{
        token.value=''
    }
    return {
        token,setToken,removeToken
    }
},{
    //将pinia的数据持久化存储
    persist:true
});