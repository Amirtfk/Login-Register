package com.example.backend.security;


import com.example.backend.model.AppUser;
import com.example.backend.model.CreateUserDto;
import com.example.backend.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    // Regi 4-2: in SecurityConfig haben wir in @Bean definiert, dass PasswordEncoder injectiert werden kann, mit implentierung
    // von BCryptPasswordEncoder, dann fügen wir den auch hier damit es injectet wird, damir wir sagen, passwordEncoder nehm das mal und hash das mal
    private PasswordEncoder passwordEncoder;
    //Regi 7: und machen wir ein Constructor, damit enjected wird
    private AppUserRepository userRepo;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder, AppUserRepository userRepo) {
        this.passwordEncoder = passwordEncoder;
        this.userRepo = userRepo;
    }


    // Regi 4: wir müssen das Password aus createUserDto Hashen
    // Regi 5: Und dann ein AppUser erstellen
    // Regi 6: dann in Datenbank schicken
    public String register(CreateUserDto createUserDto) {

        // *** Test für Checking if user exists already
/*        boolean userExistsAlready = userRepo.existsById(createUserDto.getUsername());
        if (userExistsAlready){
            throw new userExistsAlreadyException("User with username exists already:" + createUserDto.getUsername());
        }*/

        // Regi 4-1: Password Hashen with (BCrypt)
        String hashedPassword = passwordEncoder.encode(createUserDto.getPassword());

        // Regi 5: AppUser erstellen (auf basis von CreatAppUserDto) mit alle 3 Attribute
        AppUser appUser = new AppUser();
        appUser.setUsername(createUserDto.getUsername());
        appUser.setPasswordHash(hashedPassword);
        appUser.setRoles(List.of("USER")); // Die Roles ist eine List<String> deswegen List.of()
                                              // Heißt wenn eine Roles USER mitgibt, is es nur legitim. Natürlich kann man neben List.of("USER, "BlaBala") auch hinzufügen

        // Regi 6: AppUser in DB legen und
        // Regi 7: Dafür brauchen wir natürlich auch ein Repo
        return userRepo.save(appUser).getUsername();

    }
}
