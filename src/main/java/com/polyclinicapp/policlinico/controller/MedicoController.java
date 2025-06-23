package com.polyclinicapp.policlinico.controller;

import com.polyclinicapp.policlinico.service.interfaces.IServicioMedico; // Importa tu interfaz de servicio
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Anotación para indicar que es un controlador REST
@RequestMapping("/medicos") // Define la URL base para este controlador (ej. http://localhost:8080/medicos)
                            // Asegúrate de que esta URL base coincida con lo que tu frontend espera.
                            // Si en tu frontend usas /api/medicos, cambia esto a @RequestMapping("/api/medicos")
public class MedicoController {

    private final IServicioMedico servicioMedico; // Inyecta la interfaz del servicio

    // Constructor para inyección de dependencias
    public MedicoController(IServicioMedico servicioMedico) {
        this.servicioMedico = servicioMedico;
    }

    // Endpoint para eliminar un médico por su ID
    @DeleteMapping("/{id}") // Mapea las peticiones DELETE a /medicos/{id}
    @ResponseStatus(HttpStatus.NO_CONTENT) // Retorna 204 No Content para eliminación exitosa
    // @PreAuthorize("hasRole('ADMINISTRADOR')") // Agrega seguridad si solo los administradores pueden eliminar
    public void deleteMedicoById(@PathVariable Long id) {
        System.out.println("Intentando eliminar médico con ID: " + id); // Para depuración
        servicioMedico.deleteMedico(id); // Llama al método de tu servicio
    }

    // Endpoint para eliminar múltiples médicos por sus IDs (eliminación en lote)
    @DeleteMapping("/batch") // Mapea las peticiones DELETE a /medicos/batch
    @ResponseStatus(HttpStatus.NO_CONTENT) // Retorna 204 No Content
    // @PreAuthorize("hasRole('ADMINISTRADOR')") // Agrega seguridad si solo los administradores pueden eliminar
    public void deleteMedicosBatch(@RequestBody List<Long> ids) {
        System.out.println("Intentando eliminar médicos con IDs: " + ids); // Para depuración
        servicioMedico.deleteMedicosByIds(ids); // Llama al método de tu servicio
    }

    // Opcional: Si quieres un endpoint GET para depuración o para que el frontend obtenga datos
    // @GetMapping
    // public List<MedicoDTO> getAllMedicos() {
    //     return servicioMedico.findAllMedicos();
    // }

    // Opcional: Manejo de errores específico si deleteMedico() o deleteMedicosByIds() pueden lanzar excepciones
    // @ExceptionHandler(ResourceNotFoundException.class)
    // @ResponseStatus(HttpStatus.NOT_FOUND)
    // public String handleResourceNotFoundException(ResourceNotFoundException ex) {
    //     return ex.getMessage();
    // }
}