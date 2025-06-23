package com.polyclinicapp.policlinico.repository;


import com.polyclinicapp.policlinico.model.HorarioMedico;
import com.polyclinicapp.policlinico.model.Medico; // Importa Medico para el método deleteByMedico
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorioHorarioMedico extends JpaRepository<HorarioMedico, Long> {

    List<HorarioMedico> findByMedico_MedicoId(Long medicoId); 

    void deleteByMedico(Medico medico);
}