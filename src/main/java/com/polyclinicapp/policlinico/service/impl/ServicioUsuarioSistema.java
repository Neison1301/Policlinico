package com.polyclinicapp.policlinico.service.impl;

import com.polyclinicapp.policlinico.model.UsuarioSistema;
import com.polyclinicapp.policlinico.repository.RepositorioPerfilUsuario;
import com.polyclinicapp.policlinico.repository.RepositorioUsuario;
import com.polyclinicapp.policlinico.service.interfaces.IServicioUsuarioSistema;
import com.polyclinicapp.policlinico.model.PerfilUsuario;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

/*Implementación de la interfaz IServicioUsuarioSistema 
Contiene la lógica de negocio para la gestión de cuentas de usuario del sistema
 * */
@Service 
public class ServicioUsuarioSistema implements IServicioUsuarioSistema {

    private final RepositorioUsuario usuarioSistemaRepository;
    private final RepositorioPerfilUsuario perfilUsuarioRepository;
    private final PasswordEncoder passwordEncoder;

    // Constructor para inyección de dependencias
    public ServicioUsuarioSistema(RepositorioUsuario usuarioSistemaRepository,
            RepositorioPerfilUsuario perfilUsuarioRepository,
            PasswordEncoder passwordEncoder) {
        this.usuarioSistemaRepository = usuarioSistemaRepository;
        this.perfilUsuarioRepository = perfilUsuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override // Sobreescribe el método de la interfaz
    @Transactional // Asegura que la operación sea atómica (ambas tablas se actualizan o ninguna)
    public UsuarioSistema registerNewUser(String username, String password, String rolNombre, String tipoPerfil) {
        // Valida si el nombre de usuario ya existe para evitar duplicados
        if (usuarioSistemaRepository.findByUsuUsuario(username).isPresent()) {
            throw new IllegalStateException("El nombre de usuario ya existe: " + username);
        }

        // 1. Crea y guarda el objeto UsuarioSistema
        UsuarioSistema usuario = new UsuarioSistema();
        usuario.setUsuUsuario(username);
        usuario.setUsuContrasena(passwordEncoder.encode(password)); // Encripta la contraseña
        usuario.setRolNombre(rolNombre); // Este será "ROLE_ADMIN"
        UsuarioSistema savedUsuario = usuarioSistemaRepository.save(usuario);

        // 2. Crea y guarda el objeto PerfilUsuario asociado
        PerfilUsuario perfil = new PerfilUsuario();
        perfil.setUsuarioSistema(savedUsuario); // Vincula al UsuarioSistema recién creado
        perfil.setPERF_TipoPerfil(tipoPerfil); // Este será "Administrador del Sistema"
        perfil.setPERF_ID_Entidad(null); // Para el administrador, no hay una entidad de negocio asociada
        perfilUsuarioRepository.save(perfil);

        return savedUsuario;
    }

    @Override // Sobreescribe el método de la interfaz
    public Optional<UsuarioSistema> findByUsuUsuario(String username) {
        return usuarioSistemaRepository.findByUsuUsuario(username);
    }

    @Override // Sobreescribe el método de la interfaz
    public Iterable<UsuarioSistema> findAllUsuarios() {
        return usuarioSistemaRepository.findAll();
    }

    // Método para actualizar la contraseña de un usuario existente (anteriormente
    // en UsuarioService)
    @Transactional
    public UsuarioSistema actualizarContrasena(Long usuarioId, String nuevaContrasenaPlana) {
        UsuarioSistema usuarioExistente = usuarioSistemaRepository.findByUsuId(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + usuarioId));

        String nuevaContrasenaEncriptada = passwordEncoder.encode(nuevaContrasenaPlana);
        usuarioExistente.setUsuContrasena(nuevaContrasenaEncriptada);

        return usuarioSistemaRepository.save(usuarioExistente);
    }

}