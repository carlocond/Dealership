package com.dealership.Dealership.serviceimplement;

import com.dealership.Dealership.entity.User;
import com.dealership.Dealership.repository.UserRepo;
import com.dealership.Dealership.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;


    @Override
    public User create(User user) {
        return userRepo.save(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepo.findById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public void delete(Long id) {
        userRepo.deleteById(id);
    }
}
