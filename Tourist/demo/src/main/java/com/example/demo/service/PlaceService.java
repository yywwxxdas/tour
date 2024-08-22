package com.example.demo.service;

import com.example.demo.pojo.PageBean;
import com.example.demo.pojo.Place;

public interface PlaceService {
    PageBean<Place> placelist(int pageNum, int pageSize, String name, String type);
}
