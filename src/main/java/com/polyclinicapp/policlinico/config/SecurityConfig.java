package com.polyclinicapp.policlinico.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // Habilita la configuración de seguridad web
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/", "/index.html", "/css/**", "/js/**", "/image/**","/home.html").permitAll() 

                 // Rutas protegidas por rol
                 .requestMatchers("/dashboard/paciente/**").hasRole("PACIENTE")
                .requestMatchers("/dashboard/admin/**").hasRole("ADMIN")
                .requestMatchers("/dashboard/recepcionista/**").hasRole("RECEPCIONISTA")


                .anyRequest().authenticated() 
            )
            .formLogin(form -> form 
                .disable() 
            )
            .httpBasic(httpBasic -> httpBasic.disable()) 
            .csrf(csrf -> csrf.disable());

        return http.build();
    }


    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")


}