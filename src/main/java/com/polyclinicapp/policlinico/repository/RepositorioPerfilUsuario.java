package com.polyclinicapp.policlinico.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.polyclinicapp.policlinico.model.PerfilUsuario;

/*da metodos crud  Sigue el principio SRP al enfocarse solo en la persistencia de perfilUsuario*/
public interface RepositorioPerfilUsuario extends JpaRepository<PerfilUsuario, Long> {
    Optional<PerfilUsuario> findByUsuarioSistemaUsuId(Long usuId);
}