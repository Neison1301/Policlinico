package com.polyclinicapp.policlinico.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PerfilUsuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PerfilUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PERF_ID")
    private Long PERF_ID;

    @OneToOne
    @JoinColumn(name = "usu_id", referencedColumnName = "usu_id", nullable = false)
    private UsuarioSistema usuarioSistema;

    @Column(name = "PERF_TipoPerfil")
    private String PERF_TipoPerfil;

    @Column(name = "PERF_ID_Entidad")
    private Long PERF_ID_Entidad;
}