package com.example.backend.service;

import com.example.backend.model.AppUser;
import com.example.backend.repository.AppUserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class AppUserDetailService implements UserDetailsService {


    //Dependency Injection des Repositorys
    private final AppUserRepository appUserRepository;

    public AppUserDetailService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    //LoadUserByUsername = Username kommt automatisch von Spring
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = appUserRepository.findById(username) //User aus der Datenbank laden
                .orElse(null); //Optional entpacken, denn noch könnte es ja auch einfach null sein
        if (user == null){
            return null;  //Wir checken ob user = null ist und beenden die Methode ohne einen user zu returnen
        }
               // Wurde ein User gefunden, so returnen wir ihn an Spring und an die weiteren Validierungsklassen
        return new User(user.getUsername(), user.getPasswordHash(), extractSimpleGrantedAuthorities(user));
    }

    private List<SimpleGrantedAuthority> extractSimpleGrantedAuthorities(AppUser user) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (String s : user.getRoles()) {
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(s);
            authorities.add(simpleGrantedAuthority);
        }
        return authorities;
    }
}
