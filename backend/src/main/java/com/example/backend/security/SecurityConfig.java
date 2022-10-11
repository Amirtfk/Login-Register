package com.example.backend.security;


import com.example.backend.service.AppUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

   private final AppUserDetailService appUserDetailService;

    public SecurityConfig(AppUserDetailService appUserDetailService) {
        this.appUserDetailService = appUserDetailService;
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(); //Alternativ Argon2, aber Bcrypt ist auch absolut fein!
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests() // wir wollen Regeln festlegen für authorisierte Requests antmatchers=neue Regel für bestimme url!
                .antMatchers("/user/*").permitAll() // Für alle sichtbart
                .antMatchers("/api/ciao").authenticated() // Für alle eingeloggten sichtbar
                .antMatchers("/admin/*").hasRole("admin") // Nur für Nutzer der Rolle "admin" sichtbar
                .and().httpBasic();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(appUserDetailService); //authentication via userDetailsService mit Hilfe unserer Serviceklasse
    }

}
