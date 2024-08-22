package com.example.demo.service.impl;

import com.example.demo.mapper.ClimateMapper;
import com.example.demo.pojo.Climate;
import com.example.demo.pojo.PageBean;
import com.example.demo.service.ClimateService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ClimateServiceimpl implements ClimateService {
    @Autowired
    private ClimateMapper climateMapper;
    @Override
    public PageBean<Climate> climatelist(int pageNum, int pageSize) {
        PageBean<Climate> pb=new PageBean<>();
        PageHelper.startPage(pageNum,pageSize);
        List<Climate> co=climateMapper.climatelist();
        Page<Climate> p=(Page<Climate>) co;
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }
}
