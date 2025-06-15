package com.polyclinicapp.policlinico.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller 
public class RedireccionController {
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

    @GetMapping("/admin/dashboard")
    public String dashboardAdmin() {
        return "dashboard/admin";

    }

    @GetMapping("/paciente/inicio")
    public String inicioPaciente() {
        return "dashboard/paciente";
    }

    @GetMapping("/recepcionista/inicio")
    public String inicioRecepcionista() {
        return "dashboard/recepcionista";
    }



     @GetMapping("/")
    public String home() {
        return "index"; // Esto resuelve a src/main/resources/templates/index.html
    }

    @GetMapping("/especialidades")
    public String especialidades() {
        return "especialidades"; // Asegúrate de tener especialidades.html
    }

    @GetMapping("/staff-medico")
    public String staffMedico() {
        return "staff-medico"; // Asegúrate de tener staff-medico.html
    }

    @GetMapping("/contacto")
    public String contacto() {
        return "contacto"; // Asegúrate de tener contacto.html
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // Asegúrate de tener login.html
    }

    @GetMapping("/registro")
    public String registro() {
        return "registro"; // Asegúrate de tener registro.html
    }
}
