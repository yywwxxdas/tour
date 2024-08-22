package com.example.demo.mapper;

import com.example.demo.pojo.Hotel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HotelMapper {
    List<Hotel> hotellist(String name, String type);
}
