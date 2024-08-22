package com.example.demo.mapper;

import com.example.demo.pojo.Climate;
import com.example.demo.pojo.Emergency;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmergencyMapper {
    List<Emergency> emerlist();
}
