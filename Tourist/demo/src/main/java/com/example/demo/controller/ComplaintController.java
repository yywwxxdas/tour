package com.example.demo.controller;

import com.example.demo.pojo.Complaint;
import com.example.demo.pojo.PageBean;
import com.example.demo.pojo.Result;
import com.example.demo.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/complaint")
public class ComplaintController {
    @Autowired
    private ComplaintService complaintService;
    @PostMapping
    public Result add(@RequestBody Complaint complaint)//发布投诉
    {
        complaintService.add(complaint);
        return Result.success();

    }
    @GetMapping
    public Result<PageBean<Complaint>> list//显示投诉信息
            (
            int pageNum,
            int pageSize,
            @RequestParam(required = false) String status
    ){
       PageBean<Complaint> pb=complaintService.list(pageNum,pageSize,status);
       return Result.success(pb);
    }
    @DeleteMapping
    public Result delete(int id)//删除投诉
    {
        complaintService.delete(id);
        return Result.success();
    }
}
