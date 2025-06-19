package com.polyclinicapp.policlinico.service.interfaces;

import java.util.List;
import java.util.Optional;

import com.polyclinicapp.policlinico.model.Medico;
import com.polyclinicapp.policlinico.model.dto.MedicoDTO;

public interface IServicioMedico {
    
    List<MedicoDTO> findAllMedicos();
        Optional<MedicoDTO> findMedicoById(Long id);
    // Nota: El saveMedico aquí debería retornar MedicoDTO si quieres consistencia,
    // o Medico si necesitas la entidad guardada para otras operaciones en el servicio.
    // Lo mantendré retornando Medico por ahora, como lo tenías.
    Medico saveMedico(MedicoDTO medicoDTO);
    void deleteMedico(Long id);
    void deleteMedicosByIds(List<Long> ids);
}
