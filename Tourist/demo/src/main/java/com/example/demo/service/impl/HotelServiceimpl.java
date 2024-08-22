package com.example.demo.service.impl;

import com.example.demo.mapper.HotelMapper;
import com.example.demo.pojo.Hotel;
import com.example.demo.pojo.PageBean;
import com.example.demo.service.HotelService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceimpl implements HotelService {
    @Autowired
    private HotelMapper hotelMapper;
    @Override
    public PageBean<Hotel> hotellist(int pageNum, int pageSize, String name, String type) {
        PageBean<Hotel> pb=new PageBean<>();
        PageHelper.startPage(pageNum,pageSize);
        List<Hotel> co=hotelMapper.hotellist(name,type);
        Page<Hotel> p=(Page<Hotel>) co;
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }
}
