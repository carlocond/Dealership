package com.dealership.Dealership.dto.car;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CarResponseDTO { //DTO per le risposte relative alle auto
    Long id;
    String brand;
    String model;
    int year;
    String color;
    double price;
    double cc;
    double hp;
    double km;
    String imageUrl;
}

