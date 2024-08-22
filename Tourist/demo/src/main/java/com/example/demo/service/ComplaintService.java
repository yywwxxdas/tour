package com.example.demo.service;

import com.example.demo.pojo.Complaint;
import com.example.demo.pojo.PageBean;

public interface ComplaintService {
    void add(Complaint complaint);
    PageBean<Complaint> list(int pageNum, int pageSize, String status);
    void delete(int id);
}
