package com.example.demo.service;

import com.example.demo.pojo.Climate;
import com.example.demo.pojo.PageBean;

import java.util.List;

public interface ClimateService {
    PageBean<Climate> climatelist(int pageNum, int pageSize);
}
