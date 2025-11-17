package com.dealership.Dealership.serviceimplement;

import com.dealership.Dealership.dto.car.CarRequestDTO;
import com.dealership.Dealership.dto.car.CarResponseDTO;
import com.dealership.Dealership.dto.user.UserRequestDTO;
import com.dealership.Dealership.dto.user.UserResponseDTO;
import com.dealership.Dealership.entity.Role;
import com.dealership.Dealership.entity.User;
import com.dealership.Dealership.mapper.UserMapper;
import com.dealership.Dealership.mapper.CarMapper;
import com.dealership.Dealership.repository.CarRepo;
import com.dealership.Dealership.repository.UserRepo;
import com.dealership.Dealership.service.AdminService;
import com.dealership.Dealership.service.UserService;
import com.dealership.Dealership.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserRepo userRepo;
    private final CarRepo carRepo;
    private final UserService userService;
    private final CarService carService;
    private final PasswordEncoder passwordEncoder;

    //Controllo utenti

    @Override
    public UserResponseDTO createUser(UserRequestDTO request) {
        User user = UserMapper.toEntity(request);
        User saved = userService.create(user); // create codifica la password
        return UserMapper.toResponseDTO(saved);
    }

    @Override
    public UserResponseDTO updateUser(Long id, UserRequestDTO request) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // aggiorna i campi
        UserMapper.updateEntity(user, request);

        // se è stata fornita una nuova password, la codifica
        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        User saved = userRepo.save(user);
        return UserMapper.toResponseDTO(saved);
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepo.existsById(id)) {
            throw new RuntimeException("User not found");
        }
        userRepo.deleteById(id);
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return userRepo.findAll()
                .stream()
                .map(UserMapper::toResponseDTO)
                .toList();
    }

    @Override
    public UserResponseDTO changeUserRole(Long id, Role role) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User non trovato"));

        user.setRole(role);
        userRepo.save(user);

        return UserMapper.toResponseDTO(user);
    }

    //Controllo auto

    @Override
    public CarResponseDTO createCar(CarRequestDTO request) {
        var car = CarMapper.toEntity(request);
        var saved = carService.uploadCar(car);
        return CarMapper.toResponse(saved);
    }

    @Override
    public CarResponseDTO updateCar(Long id, CarRequestDTO request) {
        var car = carRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Car non trovata"));

        CarMapper.updateEntity(car, request);
        var saved = carRepo.save(car);
        return CarMapper.toResponse(saved);
    }

    @Override
    public void deleteCar(Long id) {
        if (!carRepo.existsById(id)) {
            throw new RuntimeException("Car non trovata");
        }
        carRepo.deleteById(id);
    }
}
