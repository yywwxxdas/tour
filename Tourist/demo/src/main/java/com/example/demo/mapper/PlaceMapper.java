package com.example.demo.mapper;

import com.example.demo.pojo.Place;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PlaceMapper {
    List<Place> placelist(String name, String type);
}
