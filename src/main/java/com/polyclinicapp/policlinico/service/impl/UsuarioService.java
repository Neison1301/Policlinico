package com.polyclinicapp.policlinico.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.polyclinicapp.policlinico.model.UsuarioSistema;
import com.polyclinicapp.policlinico.repository.RepositorioUsuario;

@Service
public class UsuarioService {

    private final RepositorioUsuario usuarioRepository;
    private final PasswordEncoder passwordEncoder; // Inyecta el PasswordEncoder

    // Constructor para inyección de dependencias
    public UsuarioService(RepositorioUsuario usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Método para registrar un nuevo usuario
    public UsuarioSistema registrarNuevoUsuario(UsuarioSistema nuevoUsuario) {
        // **PASO CLAVE: Encriptar la contraseña antes de guardarla**
        String contrasenaEncriptada = passwordEncoder.encode(nuevoUsuario.getUsuContrasena());
        nuevoUsuario.setUsuContrasena(contrasenaEncriptada);

        // Guarda el usuario con la contraseña encriptada en la base de datos
        return usuarioRepository.save(nuevoUsuario);
    }

    // Método para actualizar la contraseña de un usuario existente
    public UsuarioSistema actualizarContrasena(Long usuarioId, String nuevaContrasenaPlana) {
        UsuarioSistema usuarioExistente = usuarioRepository.findByUsuId(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        String nuevaContrasenaEncriptada = passwordEncoder.encode(nuevaContrasenaPlana);
        usuarioExistente.setUsuContrasena(nuevaContrasenaEncriptada);

        return usuarioRepository.save(usuarioExistente);
    }

    // Otros métodos de servicio (ej. buscar usuario, eliminar, etc.)
}