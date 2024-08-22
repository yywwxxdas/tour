package com.example.demo.service;

import com.example.demo.pojo.PageBean;
import com.example.demo.pojo.Road;

import java.util.List;

public interface RoadService {
    PageBean<Road> roadlist(int pageNum, int pageSize,String location,String status);
}
