package com.example.demo.service.impl;

import com.example.demo.mapper.EmergencyMapper;
import com.example.demo.pojo.Emergency;
import com.example.demo.pojo.PageBean;
import com.example.demo.service.EmergencyService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmergencyServiceimpl implements EmergencyService {
    @Autowired
    private EmergencyMapper emergencyMapper;
    @Override
    public PageBean<Emergency> emerlist(int pageNum, int pageSize) {
        PageBean<Emergency> pb=new PageBean<>();
        PageHelper.startPage(pageNum,pageSize);
        List<Emergency> co=emergencyMapper.emerlist();
        Page<Emergency> p=(Page<Emergency>) co;
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }
}
