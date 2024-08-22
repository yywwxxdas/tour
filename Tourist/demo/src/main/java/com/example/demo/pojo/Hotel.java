package com.example.demo.pojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Hotel {
    private int id;
    private String name;
    private String image;
    private int type;
    private int start;
    private String address;
    private String information;
    private int tourist_flow;
    private String marketing_planning;
    private String create_time;
    private String update_time;
    private int is_delete;
}
