package com.example.demo.service.impl;

import com.example.demo.mapper.PlaceMapper;
import com.example.demo.pojo.PageBean;
import com.example.demo.pojo.Place;
import com.example.demo.pojo.Road;
import com.example.demo.service.PlaceService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceServiceimpl implements PlaceService {
    @Autowired
    private PlaceMapper placeMapper;
    @Override
    public PageBean<Place> placelist(int pageNum, int pageSize, String name, String type) {
        PageBean<Place> pb=new PageBean<>();
        PageHelper.startPage(pageNum,pageSize);
        List<Place> co=placeMapper.placelist(name,type);
        Page<Place> p=(Page<Place>) co;
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }
}
