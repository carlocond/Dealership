package com.dealership.Dealership.service;

import com.dealership.Dealership.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User create (User user);
    Optional<User> findById (Long id);
    Optional<User> findByEmail (String email);
    List<User> findAll();
    void delete(Long id);
}
