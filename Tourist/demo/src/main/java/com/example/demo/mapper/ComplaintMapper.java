package com.example.demo.mapper;

import com.example.demo.pojo.Complaint;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface ComplaintMapper {

    void add(Complaint complaint);

    List<Complaint> list(String traname, String status);

    void delete(int id);
}
