package com.example.demo.entities;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Order {
    private String name;
    private String code;
    private LocalDateTime date;
    private Dimension dimension;

    @Getter
    @Setter
    public static class Dimension{
        private double  weight;
        private double  height;
        private double  width;
        private double  length;
    }
}
