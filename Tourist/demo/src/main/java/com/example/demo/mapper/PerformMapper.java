package com.example.demo.mapper;

import com.example.demo.pojo.Perform;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PerformMapper {
    List<Perform> performlist(String name, String type);
}
