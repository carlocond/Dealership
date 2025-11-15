package com.dealership.Dealership.mapper;

import com.dealership.Dealership.dto.car.CarRequestDTO;
import com.dealership.Dealership.dto.car.CarResponseDTO;
import com.dealership.Dealership.entity.Car;

public final class CarMapper { //Classe di mapping per l'entità Car, serve a convertire tra DTO e entità
    private CarMapper() {}

    public static Car toEntity(CarRequestDTO dto) { //Metodo di conversione
        if (dto == null) {
            return null;
        }
        return Car.builder()
                .brand(dto.getBrand())
                .model(dto.getModel())
                .year(dto.getYear())
                .color(dto.getColor())
                .price(dto.getPrice())
                .cc(dto.getCc())
                .hp(dto.getHp())
                .km(dto.getKm())
                .imageUrl(dto.getImageUrl())
                .build();
    }

    public static void updateEntity(Car entity, CarRequestDTO dto) { //Metodo di aggiornamento
        if (entity == null || dto == null) {
            return;
        }
        entity.setBrand(dto.getBrand());
        entity.setModel(dto.getModel());
        entity.setYear(dto.getYear());
        entity.setColor(dto.getColor());
        entity.setPrice(dto.getPrice());
        entity.setCc(dto.getCc());
        entity.setHp(dto.getHp());
        entity.setKm(dto.getKm());
        entity.setImageUrl(dto.getImageUrl());
    }

    public static CarResponseDTO toResponse(Car entity) { //Metodo di conversione in risposta DTO
        if (entity == null) {
            return null;
        }
        return CarResponseDTO.builder()
                .id(entity.getId())
                .brand(entity.getBrand())
                .model(entity.getModel())
                .year(entity.getYear())
                .color(entity.getColor())
                .price(entity.getPrice())
                .cc(entity.getCc())
                .hp(entity.getHp())
                .km(entity.getKm())
                .imageUrl(entity.getImageUrl())
                .build();
    }
}

