package com.polyclinicapp.policlinico.repository;


import com.polyclinicapp.policlinico.model.HorarioMedico;
import com.polyclinicapp.policlinico.model.Medico; // Importa Medico para el método deleteByMedico
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorioHorarioMedico extends JpaRepository<HorarioMedico, Long> {
    // Encuentra todos los horarios para un ID de médico específico
    // Usa esta nueva forma con @Query
    List<HorarioMedico> findByMedico_MedicoId(Long medicoId); // <--- ¡Así es el cambio!

    void deleteByMedico(Medico medico); // Este método ya estaba, asegúrate de que el parámetro sea Medico
}