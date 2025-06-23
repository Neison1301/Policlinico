package com.polyclinicapp.policlinico.Components; // Paquete para componentes de aplicación.

import org.springframework.boot.CommandLineRunner; // Para ejecutar código al inicio.
import org.springframework.stereotype.Component; // Marca como componente Spring.

import com.polyclinicapp.policlinico.service.impl.ServicioUsuarioSistema; // Servicio de gestión de usuarios.
/*este componente crea administradores y recepcionistas por defecto y su unica funcion es crear cuentas ya definiidads */

@Component
public class CuentaAdmin implements CommandLineRunner {

    private final ServicioUsuarioSistema usuarioSistemaService; // Inyección del servicio de usuarios.

    public CuentaAdmin(ServicioUsuarioSistema usuarioSistemaService) {
        this.usuarioSistemaService = usuarioSistemaService;
    }

    @Override
    public void run(String... args) throws Exception {

        if (usuarioSistemaService.findByUsuUsuario("admin").isEmpty()) {
            usuarioSistemaService.registerNewUser(
                    "admin",
                    "admin123",

                    "ADMIN",

                    "Administrador del Sistema");
            System.out.println("Administrador  creado.");
        } else {
            System.out.println("El usuario administrador 'admin' ya existe.");
        }

        if (usuarioSistemaService.findByUsuUsuario("recepcionista1").isEmpty()) {
            usuarioSistemaService.registerNewUser(
                    "recepcionista1",
                    "recepcionista123", // Contraseña inicial.
                    "RECEPCIONISTA", // Rol del recepcionista.
                    "Recepcionista");
            System.out.println("Usuario creado.");
        } else {
            System.out.println("El usuario  'recepcionista1' ya existe.");
        }

        // las contraseñas se encriptan en servicioUsuarioSistema, por lo que no es
        // necesario encriptarlas aqui
    }
}