package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Climate
{
    private int id;
    private String temperature;
    private String climateType;
    private String humanit;
    private String wind;
    private String ultraviolet;
    private String apparent;
    private String time;

}
