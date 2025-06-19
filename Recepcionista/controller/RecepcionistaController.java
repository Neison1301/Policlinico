package com.polyclinicapp.policlinico.controller;

import com.polyclinicapp.policlinico.model.Recepcionista;
import com.polyclinicapp.policlinico.service.RecepcionistaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recepcionistas")
public class RecepcionistaController {

    private final RecepcionistaService service;

    public RecepcionistaController(RecepcionistaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Recepcionista> listar() {
        return service.listarTodos();
    }

    @PostMapping
    public Recepcionista registrar(@RequestBody Recepcionista r) {
        return service.registrar(r);
    }

    @GetMapping("/{id}")
    public Recepcionista obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}