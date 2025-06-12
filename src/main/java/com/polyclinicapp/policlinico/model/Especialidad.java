package com.polyclinicapp.policlinico.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Especialidad")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Especialidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ESP_ID")
    private Long ESP_ID;

    @Column(name = "ESP_Nombre", nullable = false, unique = true)
    private String ESP_Nombre;

    @OneToMany(mappedBy = "especialidad")
    private List<MedicoEspecialidad> medicoEspecialidades;
}