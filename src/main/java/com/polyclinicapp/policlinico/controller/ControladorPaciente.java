package com.polyclinicapp.policlinico.controller;

import com.polyclinicapp.policlinico.service.interfaces.IServicioPaciente;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException; // Para manejar errores específicos

@RestController
@RequestMapping("/pacientes") // Define el prefijo para todos los endpoints de pacientes
@AllArgsConstructor
public class ControladorPaciente {

    private final IServicioPaciente servicioPaciente; // Inyecta la interfaz, no la implementación


    // ... otros endpoints (GET, POST, PUT) ...

    /**
     * Endpoint para eliminar un paciente por su ID.
     * Responde con 204 No Content si la eliminación es exitosa.
     * Responde con 404 Not Found si el paciente no existe.
     * Responde con 500 Internal Server Error si ocurre otro problema.
     *
     * @param id El ID del paciente a eliminar, extraído de la URL.
     * @return ResponseEntity con el estado de la operación.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPaciente(@PathVariable Long id) {
        try {
            servicioPaciente.eliminarPacientePorId(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 indica éxito sin contenido de respuesta
        } catch (NoSuchElementException e) {
            // Si el servicio lanza esta excepción (paciente no encontrado)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404
        } catch (Exception e) {
            // Para cualquier otra excepción inesperada
            System.err.println("Error al eliminar paciente con ID " + id + ": " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500
        }
    }

    /**
     * Opcional: Endpoint para eliminar múltiples pacientes por sus IDs.
     * Se usaría si el frontend envía un array de IDs.
     *
     * @param ids Lista de IDs de pacientes a eliminar, enviada en el cuerpo de la solicitud.
     * @return ResponseEntity con el estado de la operación.
     */
    @DeleteMapping("/batch") // Un endpoint diferente para eliminación por lotes
    public ResponseEntity<Void> eliminarMultiplesPacientes(@RequestBody List<Long> ids) {
        try {
            servicioPaciente.eliminarMultiplesPacientesPorIds(ids);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            // Por ejemplo, si la lista de IDs está vacía
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400
        } catch (Exception e) {
            System.err.println("Error al eliminar múltiples pacientes: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Opcional: Si implementaste borrado lógico
    // @PutMapping("/{id}/desactivar")
    // public ResponseEntity<Void> desactivarPaciente(@PathVariable Long id) {
    //     try {
    //         servicioPaciente.desactivarPaciente(id);
    //         return new ResponseEntity<>(HttpStatus.OK); // 200 OK
    //     } catch (NoSuchElementException e) {
    //         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    //     } catch (Exception e) {
    //         return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    //     }
    // }
}