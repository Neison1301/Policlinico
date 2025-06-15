package com.polyclinicapp.policlinico.service.impl;


import com.polyclinicapp.policlinico.model.UsuarioSistema;
import com.polyclinicapp.policlinico.repository.RepositorioUsuario;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Collections; // Importa Collections

/**
 * Servicio personalizado para cargar los detalles del usuario durante la autenticación.
 * Implementa la interfaz UserDetailsService de Spring Security.
 */
@Service // Indica a Spring que esta es una clase de servicio y debe ser gestionada por el contenedor.
public class UserDetailsServiceImpl implements UserDetailsService {

    private final RepositorioUsuario repositorioUsuario; // Inyecta tu repositorio de usuarios

    /**
     * Constructor para la inyección de dependencias.
     * Spring inyectará automáticamente RepositorioUsuario.
     * @param repositorioUsuario Repositorio para acceder a los datos de UsuarioSistema.
     */
    public UserDetailsServiceImpl(RepositorioUsuario repositorioUsuario) {
        this.repositorioUsuario = repositorioUsuario;
    }

    /**
     * Carga los detalles del usuario por su nombre de usuario (DNI en tu caso).
     * Este método es invocado por Spring Security durante el proceso de autenticación.
     * @param usuUsuario El nombre de usuario (DNI) proporcionado durante el intento de login.
     * @return Un objeto UserDetails que representa al usuario autenticado.
     * @throws UsernameNotFoundException Si el usuario no es encontrado en la base de datos.
     */
    @Override
    public UserDetails loadUserByUsername(String usuUsuario) throws UsernameNotFoundException {
        // Busca el usuario en la base de datos por su nombre de usuario (DNI)
        UsuarioSistema usuarioSistema = repositorioUsuario.findByUsuUsuario(usuUsuario)
            .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con DNI: " + usuUsuario));

        // Construye y retorna un objeto UserDetails de Spring Security.
        // Se añade el prefijo "ROLE_" al nombre del rol, ya que Spring Security lo espera así.
        return new org.springframework.security.core.userdetails.User(
            usuarioSistema.getUsuUsuario(), // Nombre de usuario (DNI)
            usuarioSistema.getUsuContrasena(), // Contraseña (ya encriptada)
            Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + usuarioSistema.getRolNombre())) // Lista de roles (autoridades)
        );
    }
}
