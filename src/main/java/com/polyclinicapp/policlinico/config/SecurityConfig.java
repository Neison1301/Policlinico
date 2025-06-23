package com.polyclinicapp.policlinico.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.client.RestTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.polyclinicapp.policlinico.repository.RepositorioUsuario;
import com.polyclinicapp.policlinico.service.impl.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final RepositorioUsuario repositorioUsuario;

    public SecurityConfig(RepositorioUsuario repositorioUsuario) {
        this.repositorioUsuario = repositorioUsuario;
    }

    @SuppressWarnings("deprecation")
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
        http
                .csrf(csrf -> csrf
                        .csrfTokenRepository(csrfTokenRepository())
                        .csrfTokenRequestHandler(requestHandler))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/", "/css/**", "/js/**", "/image/**", "/registro.html", "/registro",
                                "/login**", "/index.html")
                        .permitAll()
                        // Regla existente para pacientes (puede que necesites revisarla si "ADMIN" no es "ADMINISTRADOR")
                        .requestMatchers("/pacientes/**").hasRole("ADMIN")
                        // Regla para RECEPCIONISTA (si accediera a sus propias rutas específicas)
                        .requestMatchers("/recepcionista/**").hasRole("RECEPCIONISTA")
                        // Reglas de DELETE para ADMIN
                        .requestMatchers(HttpMethod.DELETE, "/medicos/**","/usuarios/**", "/pacientes/**").hasRole("ADMIN") // Cambiado a ADMINISTRADOR
                        // Regla para PACIENTE
                        .requestMatchers("/paciente/**").hasRole("PACIENTE")
                        // Regla existente para GET pacientes
                        .requestMatchers(HttpMethod.GET, "/pacientes/**")
                        .hasAnyRole("ADMIN", "RECEPCIONISTA", "PACIENTE") // Cambiado a ADMINISTRADOR
                        // *** ¡AÑADIR ESTA REGLA PARA LOS RECEPCIONISTAS DE USUARIOS! ***
                        .requestMatchers(HttpMethod.GET, "/usuarios")
                        .hasAnyRole("ADMIN", "RECEPCIONISTA") // Permite acceso a estos roles
                        // Cualquier otra ruta requiere autenticación
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/redireccion", true)
                        .failureUrl("/?error")
                        .permitAll())
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login?logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID","XSRF-TOKEN")
                        .permitAll());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl(repositorioUsuario);
    }

    @Bean
    @SuppressWarnings("deprecation")
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        return repository;
    }
}