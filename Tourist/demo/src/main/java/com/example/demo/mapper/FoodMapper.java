package com.example.demo.mapper;

import com.example.demo.pojo.Food;
import com.example.demo.pojo.Perform;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface FoodMapper {
    List<Food> foodlist(String name);
    List<Food> playlist(String name);
}
