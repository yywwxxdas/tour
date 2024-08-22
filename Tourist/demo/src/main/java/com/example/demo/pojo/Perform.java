package com.example.demo.pojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Perform {
    private int id;
    private String name;
    private String type;
    private String location;
    private String date ;
    private String phone;


}