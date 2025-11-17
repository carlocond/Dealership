package com.dealership.Dealership.service;

import com.dealership.Dealership.dto.car.CarRequestDTO;
import com.dealership.Dealership.dto.car.CarResponseDTO;
import com.dealership.Dealership.dto.user.UserRequestDTO;
import com.dealership.Dealership.dto.user.UserResponseDTO;
import com.dealership.Dealership.entity.Role;

import java.util.List;

public interface AdminService {

    //Controllo utenti
    UserResponseDTO createUser(UserRequestDTO request);
    UserResponseDTO updateUser(Long id, UserRequestDTO request);
    void deleteUser(Long id);
    List<UserResponseDTO> getAllUsers();
    UserResponseDTO changeUserRole(Long id, Role role);

    //Controllo auto
    CarResponseDTO createCar(CarRequestDTO request);
    CarResponseDTO updateCar(Long id, CarRequestDTO request);
    void deleteCar(Long id);
}
