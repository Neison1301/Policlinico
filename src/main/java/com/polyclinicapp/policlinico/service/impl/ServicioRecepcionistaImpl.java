package com.polyclinicapp.policlinico.service.impl;

import com.polyclinicapp.policlinico.model.Recepcionista;
import com.polyclinicapp.policlinico.model.dto.RecepcionistaDTO;
import com.polyclinicapp.policlinico.repository.RepositorioRecepcionista;
import com.polyclinicapp.policlinico.service.interfaces.IServicioRecepcionista;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ServicioRecepcionistaImpl implements IServicioRecepcionista {

    private final RepositorioRecepcionista repositorioRecepcionista;

    @Override
    @Transactional(readOnly = true)
    public List<RecepcionistaDTO> findAllRecepcionistas() {
        return repositorioRecepcionista.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RecepcionistaDTO> findRecepcionistaById(Long id) {
        return repositorioRecepcionista.findById(id)
                .map(this::convertToDTO);
    }

    @Override
    @Transactional
    public Recepcionista saveRecepcionista(RecepcionistaDTO recepcionistaDTO) {
        Recepcionista recepcionista = convertToEntity(recepcionistaDTO);
        return repositorioRecepcionista.save(recepcionista);
    }

    @Override
    @Transactional
    public void deleteRecepcionista(Long id) {
        repositorioRecepcionista.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteRecepcionistasByIds(List<Long> ids) {
        ids.forEach(repositorioRecepcionista::deleteById);
    }

    // --- Métodos auxiliares para conversión entre DTO y entidad ---

    private RecepcionistaDTO convertToDTO(Recepcionista r) {
        return new RecepcionistaDTO(
                r.getRecId(),
                r.getRecNombres(),
                r.getRecApellidos(),
                r.getRecDni(),
                r.getRecTelefono(),
                r.getRecCorreo()
        );
    }

    private Recepcionista convertToEntity(RecepcionistaDTO dto) {
        Recepcionista r = new Recepcionista();
        r.setRecId(dto.getId());
        r.setRecNombres(dto.getNombres());
        r.setRecApellidos(dto.getApellidos());
        r.setRecDni(dto.getDni());
        r.setRecTelefono(dto.getTelefono());
        r.setRecCorreo(dto.getCorreo());
        return r;
    }
}
// Note: Ensure that the Recepcionista entity has the appropriate getters and setters