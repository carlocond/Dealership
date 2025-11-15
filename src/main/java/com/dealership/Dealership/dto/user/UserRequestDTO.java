package com.dealership.Dealership.dto.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRequestDTO { //DTO per le richieste relative all'utente

    private String fname;

    private String lname;

    private String email;

    private String password;
}

