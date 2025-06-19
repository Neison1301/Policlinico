package com.polyclinicapp.policlinico.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "UsuarioSistema")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioSistema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usu_id")
    private Long usuId;

    @Column(name = "usu_usuario", nullable = false, unique = true)
    private String usuUsuario;

    @Column(name = "usu_contrasena", nullable = false)
    private String usuContrasena;

    @Column(name = "rolNombre", nullable = false)
    private String rolNombre;
    @Column(name = "estado", nullable = false, length = 20)
    private String estado = "activo"; 
}