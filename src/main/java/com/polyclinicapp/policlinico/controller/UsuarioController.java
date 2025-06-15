package com.polyclinicapp.policlinico.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.polyclinicapp.policlinico.model.UsuarioSistema;
import com.polyclinicapp.policlinico.service.impl.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<UsuarioSistema> registrarUsuario(@RequestBody UsuarioSistema usuario) {
        UsuarioSistema nuevoUsuario = usuarioService.registrarNuevoUsuario(usuario);
        return ResponseEntity.ok(nuevoUsuario);
    }

    // Puedes tener un endpoint para cambiar contraseña
    // @PutMapping("/cambiar-contrasena/{id}")
    // public ResponseEntity<UsuarioSistema> cambiarContrasena(@PathVariable Long id, @RequestBody String nuevaContrasena) {
    //    UsuarioSistema usuarioActualizado = usuarioService.actualizarContrasena(id, nuevaContrasena);
    //    return ResponseEntity.ok(usuarioActualizado);
    // }
}