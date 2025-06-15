package com.polyclinicapp.policlinico.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; // ¡Importante! Faltaba esta importación
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// Importa la interfaz del servicio, no la implementación concreta
import com.polyclinicapp.policlinico.service.interfaces.IServicioPaciente; // <-- Aquí está el cambio
import com.polyclinicapp.policlinico.model.dto.RegistroPacienteDTO; // Asumo que este es el DTO correcto

import jakarta.validation.Valid;
import java.util.Arrays;

@Controller
public class RegistroController {

    // Inyecta la interfaz, no la implementación
    private final IServicioPaciente pacienteService;

    // Constructor con inyección de dependencia
    public RegistroController(IServicioPaciente pacienteService) { // Inyecta la interfaz
        this.pacienteService = pacienteService;
    }

    @GetMapping({ "/registro", "/registro.html" }) // Ahora responde a ambas rutas
    public String mostrarFormularioRegistro(Model model) {
        // Siempre usa el mismo DTO para el formulario de registro
        if (!model.containsAttribute("registroForm")) {
            model.addAttribute("registroForm", new RegistroPacienteDTO());
        }
        model.addAttribute("sexos", Arrays.asList("Masculino", "Femenino", "Otro"));
        return "registro"; // Esto buscará src/main/resources/templates/registro.html
    }


    @PostMapping("/registro")
    public String procesarRegistro(@Valid @ModelAttribute("registroForm") RegistroPacienteDTO registroDTO, // Usa el DTO
                                                                                                           // correcto
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            model.addAttribute("sexos", Arrays.asList("Masculino", "Femenino", "Otro"));
            return "registro";
        }

        try {
            // Llama al método del servicio con el DTO correcto
            pacienteService.registrarNuevoPaciente(registroDTO);
            redirectAttributes.addFlashAttribute("mensajeExito", "¡Registro exitoso! Ya puedes iniciar sesión.");
            return "redirect:/"; // O la URL de tu página de login
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMensaje", e.getMessage());
            model.addAttribute("sexos", Arrays.asList("Masculino", "Femenino", "Otro"));
         
            return "registro";
        }
    }
}