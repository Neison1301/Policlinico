package com.polyclinicapp.policlinico.service.impl;

import com.polyclinicapp.policlinico.model.UsuarioSistema;
import com.polyclinicapp.policlinico.model.dto.UsuarioSistemaDTO;
import com.polyclinicapp.policlinico.repository.RepositorioPerfilUsuario;
import com.polyclinicapp.policlinico.repository.RepositorioUsuario;
import com.polyclinicapp.policlinico.service.interfaces.IServicioUsuarioSistema;

import lombok.AllArgsConstructor;

import com.polyclinicapp.policlinico.model.PerfilUsuario;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/*Implementación de la interfaz IServicioUsuarioSistema 
Contiene la lógica de negocio para la gestión de cuentas de usuario del sistema
 * */
@Service
@AllArgsConstructor
public class ServicioUsuarioSistema implements IServicioUsuarioSistema {

    private final RepositorioUsuario usuarioSistemaRepository;
    private final RepositorioPerfilUsuario perfilUsuarioRepository;
    private final PasswordEncoder passwordEncoder;

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
        usuario.setEstado("activo");

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
    public List<UsuarioSistema> findAllUsuarios() {
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

 // --- Implementación de los métodos de desactivación ---
    @Override
    @Transactional
    public void deactivateUsuarioSistema(Long id) {
        UsuarioSistema usuario = usuarioSistemaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario del sistema no encontrado con ID: " + id));

        // Cambiar el estado a "inactivo"
        usuario.setEstado("inactivo");
        usuarioSistemaRepository.save(usuario);

        // Opcional: Si quieres, también podrías cambiar el estado del PerfilUsuario asociado,
        // o eliminarlo si tu lógica de negocio lo permite y no hay dependencias.
        // Ejemplo de desactivación de perfil (si tuviera campo 'estado'):
        /*
        perfilUsuarioRepository.findByUsuarioSistema_UsuId(id).ifPresent(perfil -> {
            perfil.setEstado("inactivo"); // Asumiendo que PerfilUsuario tiene un campo 'estado'
            perfilUsuarioRepository.save(perfil);
        });
        */
        // Ejemplo de eliminación de perfil (siempre con precaución):
        // perfilUsuarioRepository.deleteByUsuarioSistema_UsuId(id); // Necesitarías @Transactional en el repo y el servicio
    }

    @Override
    @Transactional
    public void deactivateUsuariosSistemaByIds(List<Long> ids) {
        // Encontrar todos los usuarios por los IDs proporcionados
        List<UsuarioSistema> usuariosToDeactivate = usuarioSistemaRepository.findAllById(ids);

        // Cambiar el estado de cada usuario a "inactivo"
        usuariosToDeactivate.forEach(usuario -> usuario.setEstado("inactivo"));

        // Guardar todos los usuarios actualizados en la base de datos
        usuarioSistemaRepository.saveAll(usuariosToDeactivate);

        // Opcional: Deactivar o eliminar perfiles asociados en lote
        /*
        List<PerfilUsuario> perfilesToDeactivate = perfilUsuarioRepository.findByUsuarioSistema_UsuIdIn(ids);
        perfilesToDeactivate.forEach(perfil -> {
            perfil.setEstado("inactivo"); // Asumiendo que PerfilUsuario tiene un campo 'estado'
        });
        perfilUsuarioRepository.saveAll(perfilesToDeactivate);
        */
        // O: perfilUsuarioRepository.deleteAllByUsuarioSistema_UsuIdIn(ids); // Si quieres eliminar perfiles
    }

    @Override
    @Transactional(readOnly = true)
    public List<UsuarioSistemaDTO> findUsuariosByRol(String rolNombre) {
        // Aquí puedes obtener todos los usuarios y luego filtrar,
        // o si tienes un método en RepositorioUsuario que filtre por rolNombre, usarlo.
        // Por ejemplo, si tienes: `List<UsuarioSistema> findByRolNombre(String rolNombre);` en tu repositorio.
        // List<UsuarioSistema> usuarios = usuarioSistemaRepository.findByRolNombre(rolNombre);

        // Si no tienes un método directo en el repositorio para filtrar por rol, puedes hacer esto:
        return usuarioSistemaRepository.findAll().stream()
                .filter(usuario -> usuario.getRolNombre() != null && usuario.getRolNombre().equalsIgnoreCase(rolNombre))
                .map(this::convertToUsuarioSistemaDTO) // Asegúrate de tener este método convertToUsuarioSistemaDTO
                .collect(Collectors.toList());
    }

    // --- Métodos de conversión (Si usas DTOs para el frontend) ---
    // Este método es necesario para `findUsuariosByRol` si retorna List<UsuarioSistemaDTO>
    private UsuarioSistemaDTO convertToUsuarioSistemaDTO(UsuarioSistema usuario) {
        UsuarioSistemaDTO dto = new UsuarioSistemaDTO();
        dto.setId(usuario.getUsuId());
        dto.setUsuario(usuario.getUsuUsuario());
        dto.setRolNombre(usuario.getRolNombre());
        dto.setEstado(usuario.getEstado());
        // Puedes agregar otros campos aquí que sean relevantes para el frontend,
        // como el nombre y apellido si los tuvieras en UsuarioSistema o PerfilUsuario
        // y los quisieras mostrar en la tabla de recepcionistas.
        return dto;
    }

}