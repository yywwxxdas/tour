package com.example.demo.controller;

import com.example.demo.pojo.PageBean;
import com.example.demo.pojo.Result;
import com.example.demo.pojo.Road;
import com.example.demo.service.RoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/road")
public class RoadController {
    @Autowired
    private RoadService roadService;
    @GetMapping
    public Result<PageBean<Road>> list//显示路况信息
    (
            int pageNum,
            int pageSize,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String status
    ){
        PageBean<Road> pb=roadService.roadlist(pageNum,pageSize,location,status);
        return Result.success(pb);
    }
}
