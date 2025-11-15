package com.dealership.Dealership.dto.car;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CarRequestDTO { //DTO per le richieste relative alle auto

    private String brand;

    private String model;

    private int year;

    private String color;

    private double price;

    private double cc;

    private double hp;

    private double km;

    private String imageUrl;
}

