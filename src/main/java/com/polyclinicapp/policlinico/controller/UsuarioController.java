package com.polyclinicapp.policlinico.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.polyclinicapp.policlinico.model.UsuarioSistema;
import com.polyclinicapp.policlinico.model.dto.RegistroPersonalDTO;
import com.polyclinicapp.policlinico.model.dto.UsuarioSistemaDTO;
import com.polyclinicapp.policlinico.service.interfaces.IServicioUsuarioSistema;

import lombok.AllArgsConstructor;

//controlador para manejar las peticiones relacionadas con los usuarios del sistema, S 
@RestController
@AllArgsConstructor
@RequestMapping("/usuarios") 
public class UsuarioController {

    private final IServicioUsuarioSistema usuarioSistemaService;


    @PostMapping("/registrar") // Nota que aquí SÓLO se pone "/registrar"
    public ResponseEntity<UsuarioSistema> registrarUsuario(@RequestBody RegistroPersonalDTO registroDTO) {
        try {
            UsuarioSistema nuevoUsuario = usuarioSistemaService.registerNewUser(
                registroDTO.getUsername(),
                registroDTO.getPassword(),
                registroDTO.getRolNombre(),
                registroDTO.getTipoPerfil()
            );
            return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/{id}") 
    public ResponseEntity<UsuarioSistema> obtenerUsuarioPorId(@PathVariable Long id) {
        // Lógica para obtener un usuario por ID
        return ResponseEntity.ok().build();
    }

    @GetMapping 
    public ResponseEntity<Iterable<UsuarioSistema>> listarTodosLosUsuarios() {
        // Lógica para listar usuarios
        return ResponseEntity.ok().build();
    }
      // Endpoint para desactivar un usuario por ID
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public void deactivateUsuarioSistemaById(@PathVariable Long id) {
        System.out.println("Intentando desactivar usuario del sistema con ID: " + id);
        usuarioSistemaService.deactivateUsuarioSistema(id);
    }

    // Endpoint para desactivar múltiples usuarios en lote
    @DeleteMapping("/batch")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public void deactivateUsuariosSistemaBatch(@RequestBody List<Long> ids) {
        System.out.println("Intentando desactivar usuarios del sistema con IDs: " + ids);
        usuarioSistemaService.deactivateUsuariosSistemaByIds(ids);
    }

    // Endpoint para obtener la lista de recepcionistas (usuarios con rol "RECEPCIONISTA")
    // Podrías mapearlo a /usuarios/recepcionistas o /admin/recepcionistas, etc.
    // Asegúrate que tu frontend apunte a esta URL para obtener los datos de la tabla.
    @GetMapping("/recepcionistas")
    @PreAuthorize("hasAnyRole('ADMIN', 'RECEPCIONISTA')")
    public List<UsuarioSistemaDTO> getRecepcionistas() {
        return usuarioSistemaService.findUsuariosByRol("RECEPCIONISTA"); // O el valor exacto de tu rol
    }

    // Puedes añadir otros endpoints como:
    // @GetMapping("/{id}") para obtener un usuario por ID
    // @PostMapping para crear un nuevo usuario
    // @PutMapping("/{id}") para actualizar un usuario
    // @GetMapping para obtener todos los usuarios (si es necesario)
}