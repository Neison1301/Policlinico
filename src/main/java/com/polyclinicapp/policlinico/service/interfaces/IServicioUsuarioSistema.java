package com.polyclinicapp.policlinico.service.interfaces;

import java.util.List;
import java.util.Optional;

import com.polyclinicapp.policlinico.model.UsuarioSistema;
import com.polyclinicapp.policlinico.model.dto.UsuarioSistemaDTO;

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

    // --- Métodos de eliminación/desactivación ---

    /**
     * Desactiva un usuario del sistema por su ID.
     * Esta es la forma preferida de "eliminar" usuarios para mantener la integridad de los datos.
     * @param id El ID del UsuarioSistema a desactivar.
     */
    void deactivateUsuarioSistema(Long id);

    /**
     * Desactiva una lista de usuarios del sistema por sus IDs.
     * @param ids Una lista de IDs de UsuarioSistema a desactivar.
     */
    void deactivateUsuariosSistemaByIds(List<Long> ids);

    /**
     * Obtiene una lista de usuarios filtrados por su rol.
     * Útil para mostrar solo recepcionistas, por ejemplo.
     * @param rolNombre El nombre del rol por el cual filtrar (ej. "RECEPCIONISTA").
     * @return Una lista de UsuarioSistemaDTO que coinciden con el rol.
     */
    List<UsuarioSistemaDTO> findUsuariosByRol(String rolNombre);


}
