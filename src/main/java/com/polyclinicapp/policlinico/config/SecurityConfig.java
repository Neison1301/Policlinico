package com.polyclinicapp.policlinico.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.client.RestTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.polyclinicapp.policlinico.repository.RepositorioUsuario;
import com.polyclinicapp.policlinico.service.impl.UserDetailsServiceImpl;

@Configuration // Indica que esta es una clase de configuración para Spring
@EnableWebSecurity // Habilita la seguridad web de Spring Security
public class SecurityConfig {

    private final RepositorioUsuario repositorioUsuario; // Necesito el repositorio para cargar usuarios

    // Inyecto el repositorio de usuarios
    public SecurityConfig(RepositorioUsuario repositorioUsuario) {
        this.repositorioUsuario = repositorioUsuario;
    }

    @SuppressWarnings("deprecation")
    @Bean
    // Configuro la cadena de filtros de seguridad HTTP
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        // Permito el acceso a rutas públicas (inicio, estáticos, registro, login)
                        .requestMatchers("/", "/css/**", "/js/**", "/image/**", "/registro.html", "/registro",
                                "/login**",
                                "/index.html")

                        .permitAll().requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/recepcionista/**").hasRole("RECEPCIONISTA")
                        .requestMatchers("/paciente/**").hasRole("PACIENTE")
                        // Cualquier otra ruta requiere autenticación
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/") // Mi página de login es la raíz
                        .loginProcessingUrl("/login") // URL donde se envía el formulario de login
                        .defaultSuccessUrl("/redireccion", true) // A dónde voy si el login es exitoso
                        .failureUrl("/?error") // A dónde voy si el login falla
                        .permitAll())
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // <--- URL a la que se enviará la
                                                                                    // solicitud de logout
                        .logoutSuccessUrl("/login?logout") // <--- URL a la que se redirige después de cerrar sesión
                                                           // exitosamente
                        .invalidateHttpSession(true) // Invalida la sesión HTTP
                        .deleteCookies("JSESSIONID") // Borra la cookie de sesión (puede variar según el nombre de la
                                                     // cookie)
                        .permitAll());

        return http.build(); // Construyo la configuración de seguridad
    }

    @Bean
    // Defino el codificador de contraseñas (BCrypt)
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    // Configuro mi servicio para cargar los detalles del usuario
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl(repositorioUsuario); // Uso mi implementación con el repositorio
    }

    @Bean
    @SuppressWarnings("deprecation") // A veces DaoAuthenticationProvider muestra una advertencia de deprecación
    // Configuro el proveedor de autenticación basado en DAO
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService()); // Le digo qué servicio usar
        authProvider.setPasswordEncoder(passwordEncoder()); // Le digo qué codificador de contraseñas usar
        return authProvider;
    }

    @Bean
    // Bean para realizar peticiones HTTP a APIs externas
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}