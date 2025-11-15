package com.dealership.Dealership.dto.user;

import com.dealership.Dealership.entity.Role;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserResponseDTO { //DTO per le risposte relative all'utente
    Long id;
    String fname;
    String lname;
    String email;
    Role role;
}

