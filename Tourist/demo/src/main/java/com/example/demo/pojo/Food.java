package com.example.demo.pojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Food {
    private int id;
    private String name;
    private String type;
    private String introduction;
    private String location;
    private String contact;
    private String img;

}
