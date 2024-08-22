package com.example.demo.controller;


import com.example.demo.pojo.Result;
import com.example.demo.pojo.Traveler;
import com.example.demo.service.TraverlerService;
import com.example.demo.until.JwtUtil;
import com.example.demo.until.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/traveler")
public class TravelerController {
    @Autowired
    private TraverlerService traverlerService;
    @PostMapping( "/register")//游客注册
    public Result register(@RequestParam("name1") String traname, @RequestParam("password1") String trapassword)
    {
        Traveler t= traverlerService.traselect(traname);
        if(t==null){
            traverlerService.logininsert1(traname, trapassword);
            return Result.success();
        }else {
            return Result.error("账号已被占用");
        }
    }
    @PostMapping(value = "/login")//游客登录
    public Result<String> login(@RequestParam("name1") String traname,@RequestParam("password1") String trapassword)
    {
        Traveler t=traverlerService.traselect(traname);
        if(t==null){
            return Result.error("用户不存在");
        }
        if(trapassword.equals(t.getTrapassword())){
            Map<String, Object> claims=new HashMap<>();//要存放的业务数据
            claims.put("id",t.getId());//登录用户的id
            claims.put("traname",t.getTraname());//登录用户的账号
            //生成登录的token
            String token=JwtUtil.genToken(claims);
            return Result.success(token);
        }
        return Result.error("密码错误");
    }
    @GetMapping(value = "/trainfo")//游客详细信息
    public Result<Traveler> traInfo(){
        //从ThreadLocal获取用户的值
        Map<String,Object> map = ThreadLocalUtil.get();
        String traname=(String) map.get("traname");//根据用户名查询用户
        Traveler t=traverlerService.traselect(traname);
        return Result.success(t);
    }
    @PatchMapping(value = "/updatePwd")//修改密码
    public Result updatePwd(@RequestBody Map<String,String> params){
        //String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");
        if(!rePwd.equals(newPwd)){
            return Result.error("两次填写的新密码不一样");
        }
        traverlerService.updatePwd(newPwd);
        return Result.success();
    }
    @PutMapping(value = "/updatetra")//修改信息
    public Result updateTra(@RequestBody Traveler t){
        traverlerService.updatetra(t);
        return Result.success();
    }
    @PatchMapping(value = "/updateAvatar")//
    public Result updateAvatar(@RequestParam String avatarUrl){
        traverlerService.updateAvatar(avatarUrl);
        return Result.success();

    }


}
