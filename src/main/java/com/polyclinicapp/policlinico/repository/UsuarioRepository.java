package com.polyclinicapp.policlinico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.polyclinicapp.policlinico.model.UsuarioSistema;

public interface UsuarioRepository extends JpaRepository<UsuarioSistema, Integer> {
}