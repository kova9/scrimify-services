package com.scrimify.services.model.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private LocalDate dob;
    private String email;
    private String countryCode;
}
