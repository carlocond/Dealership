package com.dealership.Dealership.controller;

import com.dealership.Dealership.entity.Car;
import com.dealership.Dealership.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    //Logger per i metodi CRUD sulle auto
    private static final Logger log = Logger.getLogger(CarController.class.getName());

    @PostMapping
    public ResponseEntity<Car> uploadCar(@RequestBody Car car){
        log.info("POST /api/cars chiamato con body name {}" + car.getModel());
        Car createdCar = carService.uploadCar(car);
        return ResponseEntity.ok(createdCar);
    }

    @GetMapping
    public ResponseEntity<List<Car>> findAllCars(){
        log.info("GET /api/cars chiamato per una lista");
        return ResponseEntity.ok(carService.findAllCars());
    }

    @GetMapping("/brand/{brand}")
    public ResponseEntity<List<Car>> findCarsByBrand(@PathVariable String brand){
        log.info("GET /api/cars/brand/{} chiamato per una lista" + brand);
        return ResponseEntity.ok(carService.findCarsByBrand(brand));
    }

    @GetMapping("/year/{year}")
    public ResponseEntity<List<Car>> findCarsByYear(@PathVariable int year){
        log.info("GET /api/cars/year/{} chiamato per una lista" + year);
        return ResponseEntity.ok(carService.findCarsByYear(year));
    }

    @GetMapping("/cc/{cc}")
    public ResponseEntity<List<Car>> findCarsByCc(@PathVariable double cc){
        log.info("GET /api/cars/cc/{} chiamato per una lista" + cc);
        return ResponseEntity.ok(carService.findCarsByCc(cc));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Car> deleteCar(@PathVariable Long id){
        log.info("DELETE /api/cars/{} chiamato per eliminare un'auto" + id);
        carService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
