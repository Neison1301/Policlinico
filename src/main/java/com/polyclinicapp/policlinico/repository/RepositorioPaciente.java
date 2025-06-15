package com.polyclinicapp.policlinico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.polyclinicapp.policlinico.model.Paciente;

import java.util.Optional;

@Repository
public interface RepositorioPaciente extends JpaRepository<Paciente, Long> { // Extiende JpaRepository<Paciente, Long>
    Optional<Paciente> findByPacCorreo(String correoElectronico);
    Optional<Paciente> findByPacDni(String numeroDocumento);
}