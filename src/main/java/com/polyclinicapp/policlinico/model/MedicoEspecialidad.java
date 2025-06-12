package com.polyclinicapp.policlinico.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "MedicoEspecialidad")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicoEspecialidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MED_ESP_ID") // ID compuesto o propio de la tabla intermedia si es necesario
    private Long MED_ESP_ID; // Se asume una PK para esta tabla intermedia

    @ManyToOne
    @JoinColumn(name = "MED_ID", nullable = false)
    private Medico medico;

    @ManyToOne
    @JoinColumn(name = "ESP_ID", nullable = false)
    private Especialidad especialidad;
}
