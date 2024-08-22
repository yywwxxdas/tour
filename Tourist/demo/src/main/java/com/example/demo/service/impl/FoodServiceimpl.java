package com.example.demo.service.impl;

import com.example.demo.mapper.FoodMapper;
import com.example.demo.pojo.Food;
import com.example.demo.pojo.PageBean;
import com.example.demo.pojo.Road;
import com.example.demo.service.FoodService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodServiceimpl implements FoodService {
    @Autowired
    private FoodMapper foodMapper;

    @Override
    public PageBean<Food> foodlist(int pageNum, int pageSize, String name) {
        PageBean<Food> pb=new PageBean<>();
        PageHelper.startPage(pageNum,pageSize);
        List<Food> co=foodMapper.foodlist(name);
        Page<Food> p=(Page<Food>) co;
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }

    @Override
    public PageBean<Food> playlist(int pageNum, int pageSize, String name) {
        PageBean<Food> pb=new PageBean<>();
        PageHelper.startPage(pageNum,pageSize);
        List<Food> co=foodMapper.playlist(name);
        Page<Food> p=(Page<Food>) co;
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }
}
