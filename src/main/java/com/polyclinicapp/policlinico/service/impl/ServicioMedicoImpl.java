// Archivo: src/main/java/com/polyclinicapp/policlinico/service/impl/ServicioMedicoImpl.java
package com.polyclinicapp.policlinico.service.impl; // <--- Este es el paquete correcto

import com.polyclinicapp.policlinico.model.Medico;
import com.polyclinicapp.policlinico.model.dto.MedicoDTO;
import com.polyclinicapp.policlinico.repository.RepositorioMedico; // Asume que tienes este repositorio
import com.polyclinicapp.policlinico.service.interfaces.IServicioMedico;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service; // ¡Importante!
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Collectors;

import java.util.List;

@Service // ¡Esta anotación es indispensable! Le dice a Spring que esta clase es un bean.
@AllArgsConstructor
public class ServicioMedicoImpl implements IServicioMedico {

    private final RepositorioMedico repositorioMedico;


    @Override
    @Transactional(readOnly = true)
    public List<MedicoDTO> findAllMedicos() {
        return repositorioMedico.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<MedicoDTO> findMedicoById(Long id) {
        return repositorioMedico.findById(id).map(this::convertToDTO);
    }

    @Override
    @Transactional
    public Medico saveMedico(MedicoDTO medicoDTO) {
        Medico medico = convertToEntity(medicoDTO);
        return repositorioMedico.save(medico);
    }

    @Override
    @Transactional
    public void deleteMedico(Long id) {
        repositorioMedico.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteMedicosByIds(List<Long> ids) {
        ids.forEach(repositorioMedico::deleteById);
    }

    // --- Métodos de conversión entre Entidad y DTO ---
    private MedicoDTO convertToDTO(Medico medico) {
        MedicoDTO dto = new MedicoDTO();
        dto.setId(medico.getMedicoId());
        dto.setNombres(medico.getMED_Nombres());
        dto.setApellidos(medico.getMED_Apellidos());
        dto.setCmp(medico.getMED_CMP());
        // Ya NO se mapea horarioAtencion aquí
        return dto;
    }

    private Medico convertToEntity(MedicoDTO medicoDTO) {
        Medico medico = new Medico();
        if (medicoDTO.getId() != null) {
            medico.setMedicoId(medicoDTO.getId());
        }
        medico.setMED_Nombres(medicoDTO.getNombres());
        medico.setMED_Apellidos(medicoDTO.getApellidos());
        medico.setMED_CMP(medicoDTO.getCmp());
        // Ya NO se mapea horarioAtencion aquí
        return medico;
    }

}