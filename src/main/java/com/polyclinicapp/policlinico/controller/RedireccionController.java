package com.polyclinicapp.policlinico.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller 
public class RedireccionController {

    /** Redirecciona al dashboard según el rol del usuario tras el login. */
    @GetMapping("/redireccion")
    public String redireccionPorRol(Authentication auth) {
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
    public String dashboardAdmin() {
        return "dashboard/admin";
    }

    /** Muestra el inicio del paciente. */
    @GetMapping("/paciente/inicio")
    public String inicioPaciente() {
        return "dashboard/paciente";
    }

    /** Muestra el inicio del recepcionista. */
    @GetMapping("/recepcionista/inicio")
    public String inicioRecepcionista() {
        return "dashboard/recepcionista";
    }

    /** Muestra la página de inicio. */
    @GetMapping("/")
    public String home() {
        return "index";
    }

    /** Muestra la página de especialidades. */
    @GetMapping("/especialidades")
    public String especialidades() {
        return "especialidades"; 
    }

    /** Muestra la página del staff médico. */
    @GetMapping("/staff-medico")
    public String staffMedico() {
        return "staff-medico";
    }

    /** Muestra la página de contacto. */
    @GetMapping("/contacto")
    public String contacto() {
        return "contacto";
    }

    /** Muestra la página de login. */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /** Muestra la página de registro de nuevos usuarios. */
    @GetMapping("/registro")
    public String registro() {
        return "registro";
    }
}