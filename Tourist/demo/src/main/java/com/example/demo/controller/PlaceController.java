package com.example.demo.controller;

import com.example.demo.pojo.PageBean;
import com.example.demo.pojo.Place;
import com.example.demo.pojo.Result;
import com.example.demo.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/place")
public class PlaceController {
    @Autowired
    private PlaceService placeService;
    @GetMapping
    public Result<PageBean<Place>> list//显示景点信息
    (
            int pageNum,
            int pageSize,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String type
    ){
        PageBean<Place> pb=placeService.placelist(pageNum,pageSize,name,type);
        return Result.success(pb);
    }
}
