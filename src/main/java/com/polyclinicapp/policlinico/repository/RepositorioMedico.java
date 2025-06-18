
package com.polyclinicapp.policlinico.repository;

import com.polyclinicapp.policlinico.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioMedico extends JpaRepository<Medico, Long> {
    // JpaRepository proporciona los métodos básicos CRUD automáticamente
}