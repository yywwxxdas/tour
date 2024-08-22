package com.example.demo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Traveler {
    private int id;
    private String traname;
    @JsonIgnore
    private String trapassword;
    private String name;
    private String sex;
    private String phone;
    private String idcard;
    private String image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTraname() {
        return traname;
    }

    public void setTraname(String traname) {
        this.traname = traname;
    }

    public String getTrapassword() {
        return trapassword;
    }

    public void setTrapassword(String trapassword) {
        this.trapassword = trapassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "traveler{" +
                "id=" + id +
                ", traname='" + traname + '\'' +
                ", trapassword='" + trapassword + '\'' +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", phone='" + phone + '\'' +
                ", idcard='" + idcard + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
