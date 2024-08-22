package com.example.demo.service;

import com.example.demo.pojo.Hotel;
import com.example.demo.pojo.PageBean;

public interface HotelService {
    PageBean<Hotel> hotellist(int pageNum, int pageSize, String name, String type);
}
