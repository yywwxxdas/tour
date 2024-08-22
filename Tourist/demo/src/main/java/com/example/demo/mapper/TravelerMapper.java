package com.example.demo.mapper;

import com.example.demo.pojo.Traveler;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TravelerMapper {
    Traveler traselect(String traname);
    void logininsert1(String traname,String trapassword);
    void updatePwd(String newPwd, int id);
    void updatetra(Traveler t);
    void updateAvatar(String avatarUrl,int id);
}
