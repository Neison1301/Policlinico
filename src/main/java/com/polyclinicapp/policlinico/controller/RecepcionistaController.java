package com.polyclinicapp.policlinico.controller;

import com.polyclinicapp.policlinico.model.Recepcionista;
import com.polyclinicapp.policlinico.model.dto.RecepcionistaDTO;
import com.polyclinicapp.policlinico.service.interfaces.IServicioRecepcionista;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/recepcionistas")
@RequiredArgsConstructor
public class RecepcionistaController {

    private final IServicioRecepcionista servicio;

    @GetMapping
    public List<RecepcionistaDTO> listarTodos() {
        return servicio.findAllRecepcionistas();
    }

    @GetMapping("/{id}")
    public RecepcionistaDTO obtenerPorId(@PathVariable Long id) {
        Optional<RecepcionistaDTO> resultado = servicio.findRecepcionistaById(id);
        return resultado.orElse(null);
    }

    @PostMapping
    public Recepcionista registrar(@RequestBody RecepcionistaDTO recepcionistaDTO) {
        return servicio.saveRecepcionista(recepcionistaDTO);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        servicio.deleteRecepcionista(id);
    }

    @DeleteMapping("/eliminar-multiples")
    public void eliminarMultiples(@RequestBody List<Long> ids) {
        servicio.deleteRecepcionistasByIds(ids);
    }
}
