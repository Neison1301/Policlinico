package com.polyclinicapp.policlinico.service.impl;

import com.polyclinicapp.policlinico.model.UsuarioSistema;
import com.polyclinicapp.policlinico.repository.RepositorioUsuario;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Collections;

/*Implementación de UserDetailsService de Spring Security.
Encargada de cargar los detalles de un usuario del sistema por su nombre de usuario (DNI) 
para el proceso de autenticación de Spring Security.
*/
@Service // Esto es un servicio de Spring
public class UserDetailsServiceImpl implements UserDetailsService { // Implemento la interfaz para cargar detalles de
                                                                    // usuario

    private final RepositorioUsuario repositorioUsuario; // Necesito el repositorio para buscar usuarios

    // Constructor para que Spring inyecte el repositorio
    public UserDetailsServiceImpl(RepositorioUsuario repositorioUsuario) {
        this.repositorioUsuario = repositorioUsuario;
    }

    @Override
    // Método clave: carga el usuario por su nombre de usuario (DNI)
    public UserDetails loadUserByUsername(String usuUsuario) throws UsernameNotFoundException {
        // Busco el usuario en la base de datos por el DNI. Si no lo encuentro, lanzo
        // excepción.
        UsuarioSistema usuarioSistema = repositorioUsuario.findByUsuUsuario(usuUsuario)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con DNI: " + usuUsuario));

        if ("inactivo".equalsIgnoreCase(usuarioSistema.getEstado())) {
            System.out.println("DEBUG: Intento de inicio de sesión de usuario inactivo: " + usuUsuario);
            throw new DisabledException("La cuenta de usuario está inactiva y no puede iniciar sesión.");
        }
        // Convierto mi UsuarioSistema a un UserDetails de Spring Security
        return new org.springframework.security.core.userdetails.User(
                usuarioSistema.getUsuUsuario(), // El DNI será el nombre de usuario
                usuarioSistema.getUsuContrasena(), // La contraseña (ya encriptada)
                // Asigno el rol, añadiendo "ROLE_" como Spring Security espera
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + usuarioSistema.getRolNombre())));
    }
}