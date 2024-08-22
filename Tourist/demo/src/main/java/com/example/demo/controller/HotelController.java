package com.example.demo.controller;

import com.example.demo.pojo.Hotel;
import com.example.demo.pojo.PageBean;
import com.example.demo.pojo.Result;
import com.example.demo.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hotel")
public class HotelController {
    @Autowired
    private HotelService hotelService;
    @GetMapping
    public Result<PageBean<Hotel>> list//显示酒店信息
    (
            int pageNum,
            int pageSize,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String type
    ){
        PageBean<Hotel> pb=hotelService.hotellist(pageNum,pageSize,name,type);
        return Result.success(pb);
    }
}
