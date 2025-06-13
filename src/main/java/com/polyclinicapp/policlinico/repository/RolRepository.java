package com.polyclinicapp.policlinico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.polyclinicapp.policlinico.model.Rol;


@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
}