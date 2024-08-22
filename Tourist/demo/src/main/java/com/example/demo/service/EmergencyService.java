package com.example.demo.service;

import com.example.demo.pojo.Emergency;
import com.example.demo.pojo.PageBean;

public interface EmergencyService {
    PageBean<Emergency> emerlist(int pageNum, int pageSize);
}
