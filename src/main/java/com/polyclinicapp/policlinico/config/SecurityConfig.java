package com.polyclinicapp.policlinico.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.RestTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.polyclinicapp.policlinico.repository.RepositorioUsuario;
import com.polyclinicapp.policlinico.service.impl.UserDetailsServiceImpl; // Asegúrate que esta ruta es correcta

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final RepositorioUsuario repositorioUsuario; // Inyecta el repositorio aquí

    // Constructor para inyección de dependencias
    public SecurityConfig(RepositorioUsuario repositorioUsuario) {
        this.repositorioUsuario = repositorioUsuario;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        // Permite acceso público a la página de inicio, recursos estáticos y la URL de
                        // login
                        .requestMatchers("/", "/css/**", "/js/**", "/image/**", "/registro.html", "/registro", "/login**",
                                "/index.html")
                        .permitAll()
                        // Cualquier otra solicitud requiere autenticación
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/") // La URL de tu página de inicio donde está el formulario de login
                        .loginProcessingUrl("/login") // La URL a la que el formulario POST de login envía los datos
                        .defaultSuccessUrl("/redireccion", true) // Redirige aquí tras un login exitoso
                        .failureUrl("/?error") // Redirige aquí tras un login fallido, con un parámetro de error
                        .permitAll() // Permite el acceso a la página de login (la misma que loginPage)
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // URL para cerrar sesión
                        .logoutSuccessUrl("/?logout") // Redirige tras cerrar sesión, con un parámetro de logout
                        .permitAll());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean // Este bean ahora usará la clase UserDetailsServiceImpl que creaste.
    public UserDetailsService userDetailsService() {
        // Inyecta el repositorioUsuario en el constructor de tu UserDetailsServiceImpl
        return new UserDetailsServiceImpl(repositorioUsuario);
    }

    @Bean
    @SuppressWarnings("deprecation") // Se usa para suprimir la advertencia si DaoAuthenticationProvider es
                                     // deprecated
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService()); // Usa tu UserDetailsService
        authProvider.setPasswordEncoder(passwordEncoder()); // Usa tu PasswordEncoder
        return authProvider;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}