package com.example.demo.pojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Emergency {
    private int id;
    private String title;
    private String content;
    private String state;
    private String createtime;
    private String updatetime;
    private String img;
    private int createuserid;
}
