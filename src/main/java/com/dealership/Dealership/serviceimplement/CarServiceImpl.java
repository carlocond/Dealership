package com.dealership.Dealership.serviceimplement;

import com.dealership.Dealership.entity.Car;
import com.dealership.Dealership.repository.CarRepo;
import com.dealership.Dealership.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepo carRepo;

    @Override
    public Car uploadCar(Car car) {
        return carRepo.save(car);
    }

    @Override
    public List<Car> findAllCars() {
        return carRepo.findAll();
    }

    @Override
    public List<Car> findCarsByBrand(String brand) {
        return carRepo.findByBrand(brand);
    }

    @Override
    public List<Car> findCarsByYear(int year) {
        return carRepo.findByYear(year);
    }

    @Override
    public List<Car> findCarsByCc(double cc) {
        return carRepo.findByCc(cc);
    }

    @Override
    public void delete(Long id) {
        carRepo.deleteById(id);
    }


}
