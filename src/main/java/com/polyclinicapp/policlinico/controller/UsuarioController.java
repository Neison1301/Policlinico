package com.polyclinicapp.policlinico.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RestController;

import com.polyclinicapp.policlinico.model.UsuarioSistema;
import com.polyclinicapp.policlinico.model.dto.RegistroPersonalDTO;
import com.polyclinicapp.policlinico.service.interfaces.IServicioUsuarioSistema;

//controlador para manejar las peticiones relacionadas con los usuarios del sistema, S 
@RestController
@RequestMapping("/usuarios") 
public class UsuarioController {

    private final IServicioUsuarioSistema usuarioSistemaService;

    public UsuarioController(IServicioUsuarioSistema usuarioSistemaService) {
        this.usuarioSistemaService = usuarioSistemaService;
    }

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
}