package com.example.demo.controller;

import com.example.demo.pojo.Emergency;
import com.example.demo.pojo.PageBean;
import com.example.demo.pojo.Result;
import com.example.demo.service.EmergencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/emergency")
public class EmergencyController {
    @Autowired
    private EmergencyService emergencyService;
    @GetMapping
    public Result<PageBean<Emergency>> list//显示应急事件信息
    (
            int pageNum,
            int pageSize
    ){
        PageBean<Emergency> pb=emergencyService.emerlist(pageNum,pageSize);
        return Result.success(pb);
    }
}
