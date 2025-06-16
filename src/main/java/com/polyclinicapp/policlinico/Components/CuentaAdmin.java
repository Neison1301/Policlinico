package com.polyclinicapp.policlinico.Components; // Paquete para componentes de aplicación.

import org.springframework.boot.CommandLineRunner; // Para ejecutar código al inicio.
import org.springframework.stereotype.Component; // Marca como componente Spring.

import com.polyclinicapp.policlinico.service.impl.ServicioUsuarioSistema; // Servicio de gestión de usuarios.

@Component // Componente para inicialización.
public class CuentaAdmin implements CommandLineRunner {

    private final ServicioUsuarioSistema usuarioSistemaService; // Inyección del servicio de usuarios.

    public CuentaAdmin(ServicioUsuarioSistema usuarioSistemaService) {
        this.usuarioSistemaService = usuarioSistemaService;
    }

    @Override
    public void run(String... args) throws Exception {
        // Crea 'admin' si no existe.
        if (usuarioSistemaService.findByUsuUsuario("admin").isEmpty()) {
            usuarioSistemaService.registerNewUser(
                "admin",
                "admin123", // Contraseña inicial.
                "ADMIN",    // Rol del administrador.
                "Administrador del Sistema"
            );
            System.out.println("Administrador predeterminado 'admin' creado.");
        } else {
            System.out.println("El usuario administrador 'admin' ya existe.");
        }

        // Crea 'recepcionista1' si no existe.
        if (usuarioSistemaService.findByUsuUsuario("recepcionista1").isEmpty()) {
            usuarioSistemaService.registerNewUser(
                "recepcionista1",
                "recepcionista123", // Contraseña inicial.
                "RECEPCIONISTA",    // Rol del recepcionista.
                "Recepcionista"
            );
            System.out.println("Usuario recepcionista 'recepcionista1' creado.");
        } else {
            System.out.println("El usuario recepcionista 'recepcionista1' ya existe.");
        }

        // Importante: Asegurarse que las contraseñas se encripten al registrarse.
    }
}