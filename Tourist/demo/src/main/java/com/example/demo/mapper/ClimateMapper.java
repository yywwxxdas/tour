package com.example.demo.mapper;

import com.example.demo.pojo.Climate;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClimateMapper {
    List<Climate> climatelist();
}
