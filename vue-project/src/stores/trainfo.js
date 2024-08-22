import {defineStore} from 'pinia'
import {ref} from 'vue'
const useTraInfostore=defineStore('trainfo',()=>{
    //定义状态相关的内容
    const info=ref({})
    //修改状态的值
    const setInfo=(newInfo)=>{
        info.value=newInfo
    }
    //移除状态的值
    const removeInfo=()=>{
        info.value={}
    }
    return {info,setInfo,removeInfo}

},{persist:true})//持久化参数
export default useTraInfostore;