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
@Table(name = "Medico")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MED_ID")
    private Long MED_ID;

    @Column(name = "MED_Nombres", nullable = false)
    private String MED_Nombres;

    @Column(name = "MED_Apellidos", nullable = false)
    private String MED_Apellidos;

    @Column(name = "MED_CMP", nullable = false, unique = true)
    private String MED_CMP;

    @Column(name = "MED_HorarioAtencion")
    private String MED_HorarioAtencion;

    @OneToMany(mappedBy = "medico")
    private List<Cita> citas;

    @OneToMany(mappedBy = "medico")
    private List<Historial_Medico> historialMedico;

    @OneToMany(mappedBy = "medico")
    private List<MedicoEspecialidad> medicoEspecialidades;
}