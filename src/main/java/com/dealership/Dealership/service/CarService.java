package com.dealership.Dealership.service;

import com.dealership.Dealership.entity.Car;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface CarService {
    Car uploadCar(Car car);
    List<Car> findAllCars();
    List<Car> findCarsByBrand(String brand);
    List<Car> findCarsByYear(int year);
    List<Car> findCarsByCc(double cc);
    void delete(Long id);
}
