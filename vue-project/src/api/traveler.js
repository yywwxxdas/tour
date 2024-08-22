import request from '../untils/request.js'
//调用注册接口的函数
export const traregisterService=(registerdata)=>{
    const params=new URLSearchParams()
    for(let key in registerdata){
        params.append(key,registerdata[key]);
    }
    return request.post('/traveler/register',params);
}
//调用登录接口的函数
export const traloginService=(logindata)=>{
    const params=new URLSearchParams()
    for(let key in logindata){
        params.append(key,logindata[key])
    }
    return request.post('/traveler/login',params);
}
//调用获取用户信息
export const travelerInfoService=()=>{
    return request.get('/traveler/trainfo')
}
//重置密码
export const traPasswordUpdateService = (password) => {
    return request.patch('/traveler/updatePwd',password)
}
//修改信息
export const traInfoUpdateService=(updatedata)=>{
    return request.put('/traveler/updatetra',updatedata)
}
//修改图片
export const traAvatarUpdateService=(avatarUrl)=>{
    const params=new URLSearchParams();
    params.append('avatarUrl',avatarUrl)
    return request.patch('/traveler/updateAvatar',params)
}