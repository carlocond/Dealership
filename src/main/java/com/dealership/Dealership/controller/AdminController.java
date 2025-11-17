package com.dealership.Dealership.controller;

import com.dealership.Dealership.dto.car.CarRequestDTO;
import com.dealership.Dealership.dto.car.CarResponseDTO;
import com.dealership.Dealership.dto.user.UserRequestDTO;
import com.dealership.Dealership.dto.user.UserResponseDTO;
import com.dealership.Dealership.entity.Role;
import com.dealership.Dealership.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admins")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/users")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO request) {
        return ResponseEntity.ok(adminService.createUser(request));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(
            @PathVariable Long id,
            @RequestBody UserRequestDTO request) {
        return ResponseEntity.ok(adminService.updateUser(id, request));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        adminService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(adminService.getAllUsers());
    }

    @PutMapping("/users/{id}/role")
    public ResponseEntity<UserResponseDTO> changeRole(
            @PathVariable Long id,
            @RequestParam Role role) {
        return ResponseEntity.ok(adminService.changeUserRole(id, role));
    }

    // ================= CAR =================

    @PostMapping("/cars")
    public ResponseEntity<CarResponseDTO> createCar(@RequestBody CarRequestDTO request) {
        return ResponseEntity.ok(adminService.createCar(request));
    }

    @PutMapping("/cars/{id}")
    public ResponseEntity<CarResponseDTO> updateCar(
            @PathVariable Long id,
            @RequestBody CarRequestDTO request) {
        return ResponseEntity.ok(adminService.updateCar(id, request));
    }

    @DeleteMapping("/cars/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        adminService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }
}
