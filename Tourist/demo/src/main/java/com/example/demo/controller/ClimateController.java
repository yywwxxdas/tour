package com.example.demo.controller;

import com.example.demo.pojo.Climate;
import com.example.demo.pojo.PageBean;
import com.example.demo.pojo.Result;
import com.example.demo.service.ClimateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/climate")
public class ClimateController {
    @Autowired
    private ClimateService climateService;
    @GetMapping
    public Result<PageBean<Climate>> list//显示天气信息
    (
            int pageNum,
            int pageSize
    ){
        PageBean<Climate> pb=climateService.climatelist(pageNum,pageSize);
        return Result.success(pb);
    }

}
