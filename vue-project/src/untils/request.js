import axios from 'axios';
import {useTokenStore} from '../stores/token.js'
//定义一个变量,记录公共的前缀,baseURL
const baseURL = '/api';
const instance = axios.create({baseURL})
 
//添加请求拦截器
instance.interceptors.request.use(
    (config)=>{
        const tokenStore=useTokenStore();//添加token
        if(tokenStore.token){//判断有没有token
            config.headers.Authorization=tokenStore.token//定义统一请求头
        }
        return config;
    },
    (err)=>{
        Promise.reject(err)
    }
)
//添加响应拦截器
instance.interceptors.response.use(
    result=>{
        return result.data;
    },
    err=>{
        alert('服务异常');
        return Promise.reject(err);//异步的状态转化成失败的状态
    }
)
 
export default instance;