package com.example.demo.pojo;

import java.time.LocalDateTime;

public class Complaint {
    private int id;
    private String content;
    private String img;
    private String comname;
    private LocalDateTime createtime;
    private String status;
    private String evaluate;
    private String consequence;
    private int grade;
    private LocalDateTime handingcreatetime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getComname() {
        return comname;
    }

    public void setComname(String comname) {
        this.comname = comname;
    }

    public LocalDateTime getCreatetime() {
        return createtime;
    }

    public void setCreatetime(LocalDateTime createtime) {
        this.createtime = createtime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(String evaluate) {
        this.evaluate = evaluate;
    }

    public String getConsequence() {
        return consequence;
    }

    public void setConsequence(String consequence) {
        this.consequence = consequence;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public LocalDateTime getHandingcreatetime() {
        return handingcreatetime;
    }

    public void setHandingcreatetime(LocalDateTime handingcreatetime) {
        this.handingcreatetime = handingcreatetime;
    }
}
