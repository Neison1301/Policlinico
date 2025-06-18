package com.polyclinicapp.policlinico.service.interfaces;

import java.util.List;
import java.util.Optional;

import com.polyclinicapp.policlinico.model.UsuarioSistema;

/**
 * Interfaz para la gestión de usuarios del sistema cuentas de
 * login y perfiles.
 * Define el contrato para las operaciones relacionadas con UsuarioSistema y
 * cumple con los principios O y D.
 */
public interface IServicioUsuarioSistema {
    // Método para registrar un nuevo usuario (admin en tu caso).
    UsuarioSistema registerNewUser(String username, String password, String rolNombre, String tipoPerfil);

    // Método para buscar un usuario por su nombre de usuario.
    Optional<UsuarioSistema> findByUsuUsuario(String username);

    // Método para obtener todos los usuarios (útil para la gestión del admin).
    List<UsuarioSistema> findAllUsuarios();

}
