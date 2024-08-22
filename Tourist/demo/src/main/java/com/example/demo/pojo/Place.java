package com.example.demo.pojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Place {
    private int id;
    private String name;
    private String introduction;
    private String type;
    private String price;
    private String img;
}
