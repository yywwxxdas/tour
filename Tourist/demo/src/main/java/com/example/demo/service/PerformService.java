package com.example.demo.service;

import com.example.demo.pojo.PageBean;
import com.example.demo.pojo.Perform;

import java.util.List;

public interface PerformService {
    PageBean<Perform> performlist(int pageNum, int pageSize, String name, String type);
}
