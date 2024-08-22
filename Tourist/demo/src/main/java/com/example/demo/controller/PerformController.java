package com.example.demo.controller;

import com.example.demo.pojo.PageBean;
import com.example.demo.pojo.Perform;
import com.example.demo.pojo.Result;
import com.example.demo.service.PerformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/perform")
public class PerformController {
    @Autowired
    private PerformService performService;
    @GetMapping
    public Result<PageBean<Perform>> list//显示演出信息
    (
            int pageNum,
            int pageSize,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String type
    ){
        PageBean<Perform> pb=performService.performlist(pageNum,pageSize,name,type);
        return Result.success(pb);
    }
}
