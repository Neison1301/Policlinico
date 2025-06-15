package com.polyclinicapp.policlinico.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.polyclinicapp.policlinico.model.UsuarioSistema;

public interface RepositorioUsuario extends JpaRepository<UsuarioSistema, Long> {
    Optional<UsuarioSistema> findByUsuUsuario(String usuUsuario);

    Optional<UsuarioSistema> findByUsuId(Long usuId);
}