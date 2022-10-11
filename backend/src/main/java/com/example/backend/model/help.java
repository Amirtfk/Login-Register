package com.example.backend.model;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class help {

    public static void main(String[] args) {
        // Hilfsklasse, solange wir unsere User von Hand in die DB coden um den PasswordHash zu erzeugen.
        System.out.println(new BCryptPasswordEncoder().encode("Password"));     }
}
