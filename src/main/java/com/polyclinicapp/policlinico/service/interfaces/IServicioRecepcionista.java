package com.polyclinicapp.policlinico.service.interfaces;

import com.polyclinicapp.policlinico.model.Recepcionista;
import com.polyclinicapp.policlinico.model.dto.RecepcionistaDTO;

import java.util.List;
import java.util.Optional;

public interface IServicioRecepcionista {

    List<RecepcionistaDTO> findAllRecepcionistas();

    Optional<RecepcionistaDTO> findRecepcionistaById(Long id);

    Recepcionista saveRecepcionista(RecepcionistaDTO recepcionistaDTO);

    void deleteRecepcionista(Long id);

    void deleteRecepcionistasByIds(List<Long> ids);
}

