package com.example.demo.service;

import com.example.demo.pojo.Traveler;

public interface TraverlerService {
    Traveler traselect(String traname);
    void logininsert1(String traname,String trapassword);
    void updatePwd(String newPwd);
    void updatetra(Traveler t);
    void updateAvatar(String avatarUrl);
}
