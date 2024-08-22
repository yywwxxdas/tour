package com.example.demo.service.impl;

import com.example.demo.mapper.PerformMapper;
import com.example.demo.pojo.PageBean;
import com.example.demo.pojo.Perform;
import com.example.demo.pojo.Road;
import com.example.demo.service.PerformService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PerformServiceimpl implements PerformService {
    @Autowired
    private PerformMapper performMapper;
    @Override
    public PageBean<Perform> performlist(int pageNum, int pageSize, String name, String type) {
        PageBean<Perform> pb=new PageBean<>();
        PageHelper.startPage(pageNum,pageSize);
        List<Perform> co=performMapper.performlist(name,type);
        Page<Perform> p=(Page<Perform>) co;
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }
}
