package com.polyclinicapp.policlinico.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpServletRequest;

import com.polyclinicapp.policlinico.service.interfaces.IServicioUsuarioSistema;

/**
 * Controlador que maneja las redirecciones de usuarios después de un login
 * exitoso
 * segun sus roles, y prepara los datos para los dashboards correspondientes.
 * Es parte de la capa de cntrol en el MVC.
 */
// necesita optimizar
@Controller
public class RedireccionController {

    private final IServicioUsuarioSistema usuarioSistemaService; // Inyección de dependencia del servicio de usuarios.

    public RedireccionController(IServicioUsuarioSistema usuarioSistemaService) {
        this.usuarioSistemaService = usuarioSistemaService;
    }

    @GetMapping("/redireccion")

    public String redireccionPorRol(Authentication auth) {
        // Obtiene e rol del usuario autenticado
        String rol = auth.getAuthorities().iterator().next().getAuthority();

        switch (rol) {
            case "ROLE_ADMIN":
                return "redirect:/admin/dashboard";
            case "ROLE_PACIENTE":
                return "redirect:/paciente/inicio";
            case "ROLE_RECEPCIONISTA":
                return "redirect:/recepcionista/inicio";
            default:
                return "redirect:/login?error";
        }
    }

    /** Muestra el dashboard del administrador. */
    @GetMapping("/admin/dashboard")
    public String dashboardAdmin(Authentication auth, Model model, HttpServletRequest request) {
        String username = auth.getName();
        usuarioSistemaService.findByUsuUsuario(username).ifPresent(usuarioSistema -> {
            String tipoPerfil = usuarioSistema.getRolNombre();
            model.addAttribute("tipoPerfil", tipoPerfil);
        });
        model.addAttribute("currentUri", request.getRequestURI());
        return "dashboard/admin/gestionPaciente"; // Ruta a la plantilla HTML.
    }

    /** Muestra la página de inicio del paciente. */
    @GetMapping("/paciente/inicio")
    public String inicioPaciente(Authentication auth, Model model, HttpServletRequest request) {
        String username = auth.getName();
        usuarioSistemaService.findByUsuUsuario(username).ifPresent(usuarioSistema -> {
            String tipoPerfil = usuarioSistema.getRolNombre();
            model.addAttribute("tipoPerfil", tipoPerfil);
        });
        model.addAttribute("currentUri", request.getRequestURI());
        // ¡CAMBIO AQUÍ! Apunta al archivo inicio.html dentro de la carpeta 'paciente'

        return "dashboard/paciente/inicio"; //
    }

    /** Muestra la página de inicio del recepcionista. */
    @GetMapping("/recepcionista/inicio")
    public String inicioRecepcionista(Authentication auth, Model model, HttpServletRequest request) {
        String username = auth.getName();
        usuarioSistemaService.findByUsuUsuario(username).ifPresent(usuarioSistema -> {
            String tipoPerfil = usuarioSistema.getRolNombre();
            model.addAttribute("tipoPerfil", tipoPerfil);
        });
        model.addAttribute("currentUri", request.getRequestURI());
        // ¡CAMBIO AQUÍ! Apunta al archivo inicio.html dentro de la carpeta
        // 'recepcionista'
        return "dashboard/recepcionista/inicio"; //
    }
}