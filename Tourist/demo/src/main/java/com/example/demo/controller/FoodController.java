package com.example.demo.controller;


import com.example.demo.pojo.Food;
import com.example.demo.pojo.PageBean;
import com.example.demo.pojo.Result;
import com.example.demo.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/canyinyvle")
public class FoodController {
    @Autowired
    private FoodService foodService;
    @GetMapping(value = "/food")
    public Result<PageBean<Food>> list//显示餐饮信息
    (
            int pageNum,
            int pageSize,
            @RequestParam(required = false) String name
    ){
        PageBean<Food> pb=foodService.foodlist(pageNum,pageSize,name);
        return Result.success(pb);
    }
    @GetMapping(value = "/play")
    public Result<PageBean<Food>> list1//显示娱乐信息
    (
            int pageNum,
            int pageSize,
            @RequestParam(required = false) String name
    ){
        PageBean<Food> pb=foodService.playlist(pageNum,pageSize,name);
        return Result.success(pb);
    }

}
