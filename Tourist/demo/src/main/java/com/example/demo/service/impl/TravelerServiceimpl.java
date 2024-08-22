package com.example.demo.service.impl;

import com.example.demo.mapper.TravelerMapper;
import com.example.demo.pojo.Traveler;
import com.example.demo.service.TraverlerService;
import com.example.demo.until.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TravelerServiceimpl implements TraverlerService {
    @Autowired
    private TravelerMapper travelerMapper;

    @Override
    public Traveler traselect(String traname) {
        Traveler t=travelerMapper.traselect(traname);
        return t;
    }

    @Override
    public void logininsert1(String traname, String trapassword) {
        travelerMapper.logininsert1(traname,trapassword);
    }
    @Override
    public void updatePwd(String newPwd) {
        Map<String,Object> map= ThreadLocalUtil.get();
        int id=(int) map.get("id");
        travelerMapper.updatePwd(newPwd,id);
    }
    @Override
    public void updatetra(Traveler t) {
        travelerMapper.updatetra(t);
    }

    @Override
    public void updateAvatar(String avatarUrl) {
        Map<String,Object> map=ThreadLocalUtil.get();
        int id=(int) map.get("id");
        travelerMapper.updateAvatar(avatarUrl,id);
    }


}
