package com.example.backend.model;


import lombok.Data;


// Regi 2: Hier in UserDto können wir sicher stellen, dass niemand eine Rolle mitgibt z.B Admin, die eine Person nicht hat
@Data
public class CreateUserDto {

    private String username;
    private String password;
}
