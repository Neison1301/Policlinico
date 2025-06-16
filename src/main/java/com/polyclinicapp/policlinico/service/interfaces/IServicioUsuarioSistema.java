package com.polyclinicapp.policlinico.service.interfaces;

import java.util.Optional;

import com.polyclinicapp.policlinico.model.UsuarioSistema;

public interface IServicioUsuarioSistema {
        // Método para registrar un nuevo usuario (admin en tu caso).
    UsuarioSistema registerNewUser(String username, String password, String rolNombre, String tipoPerfil);

    // Método para buscar un usuario por su nombre de usuario.
    Optional<UsuarioSistema> findByUsuUsuario(String username);

    // Método para obtener todos los usuarios (útil para la gestión del admin).
    Iterable<UsuarioSistema> findAllUsuarios();
    
}
