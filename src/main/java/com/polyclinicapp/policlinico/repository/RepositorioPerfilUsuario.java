package com.polyclinicapp.policlinico.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.polyclinicapp.policlinico.model.PerfilUsuario;

public interface RepositorioPerfilUsuario extends JpaRepository<PerfilUsuario, Long> {
    Optional<PerfilUsuario> findByUsuarioSistemaUsuId(Long usuId);
}