package com.example.demo.service;

import com.example.demo.pojo.Food;
import com.example.demo.pojo.PageBean;


public interface FoodService {
    PageBean<Food> foodlist(int pageNum, int pageSize, String name);
    PageBean<Food> playlist(int pageNum, int pageSize, String name);
}
