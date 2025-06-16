package com.polyclinicapp.policlinico.repository;

import com.polyclinicapp.policlinico.model.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RepositorioCita extends JpaRepository<Cita, Long> { // O RepositorioCita, si ese es tu nombre

}