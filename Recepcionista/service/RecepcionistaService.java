package com.polyclinicapp.policlinico.service;

import com.polyclinicapp.policlinico.model.Recepcionista;
import com.polyclinicapp.policlinico.repository.RecepcionistaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecepcionistaService {

    private final RecepcionistaRepository repo;

    public RecepcionistaService(RecepcionistaRepository repo) {
        this.repo = repo;
    }

    public List<Recepcionista> listarTodos() {
        return repo.findAll();
    }

    public Optional<Recepcionista> obtenerPorId(Long id) {
        return repo.findById(id);
    }

    public Recepcionista registrar(Recepcionista r) {
        return repo.save(r);
    }

    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}