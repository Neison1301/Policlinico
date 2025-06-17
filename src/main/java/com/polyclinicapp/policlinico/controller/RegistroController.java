package com.polyclinicapp.policlinico.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.polyclinicapp.policlinico.service.interfaces.IServicioPaciente;
import com.polyclinicapp.policlinico.model.dto.RegistroPacienteDTO;

import jakarta.validation.Valid;
import java.util.Arrays;

/*esta clase se encasrga de manejar el registro deusuaio usando el dto para la entrada de dtaos */

@Controller
public class RegistroController {

    private final IServicioPaciente pacienteService;// Inyección de la interfaz del servicio de pacientes.
                                                    // Esto cumple con el S D;

    public RegistroController(IServicioPaciente pacienteService) { 
        this.pacienteService = pacienteService;
    }

    @GetMapping({ "/registro", "/registro.html" }) // Ahora responde a ambas rutas
    public String mostrarFormularioRegistro(Model model) {
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