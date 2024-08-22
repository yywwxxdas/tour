package com.example.demo.service.impl;

import com.example.demo.mapper.RoadMapper;
import com.example.demo.pojo.PageBean;
import com.example.demo.pojo.Road;
import com.example.demo.service.RoadService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoadServiceimpl implements RoadService {
    @Autowired
    private RoadMapper roadMapper;
    @Override
    public PageBean<Road> roadlist(int pageNum, int pageSize,String location,String status) {
        PageBean<Road> pb=new PageBean<>();
        PageHelper.startPage(pageNum,pageSize);
        List<Road> co=roadMapper.roadlist(location,status);
        Page<Road> p=(Page<Road>) co;
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }
}
