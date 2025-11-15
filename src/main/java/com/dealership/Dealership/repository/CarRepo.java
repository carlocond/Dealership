package com.dealership.Dealership.repository;

import com.dealership.Dealership.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarRepo extends JpaRepository<Car, Long> {

    List<Car> findByBrand(String brand);
    List<Car> findByYear(int year);
    List<Car> findByCC(double cc);
    List<Car> findAll();

}
