package com.example.demo.service.impl;

import com.example.demo.mapper.ComplaintMapper;
import com.example.demo.pojo.Complaint;
import com.example.demo.pojo.PageBean;
import com.example.demo.service.ComplaintService;
import com.example.demo.until.ThreadLocalUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ComplaintServiceimpl implements ComplaintService {
    @Autowired
    private ComplaintMapper complaintMapper;

    @Override
    public void add(Complaint complaint) {
        complaint.setCreatetime(LocalDateTime.now());
        Map<String,Object> map=ThreadLocalUtil.get();
        String traname=(String) map.get("traname");
        complaint.setComname(traname);
        complaintMapper.add(complaint);
    }

    @Override
    public PageBean<Complaint> list(int pageNum, int pageSize, String status) {
        PageBean<Complaint> pb=new PageBean<>();
        PageHelper.startPage(pageNum,pageSize);
        Map<String,Object> map=ThreadLocalUtil.get();
        String traname=(String) map.get("traname");
        List<Complaint> co=complaintMapper.list(traname,status);
        Page<Complaint> p=(Page<Complaint>) co;
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }

    @Override
    public void delete(int id) {
        complaintMapper.delete(id);
    }
}
