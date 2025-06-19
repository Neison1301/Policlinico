package com.polyclinicapp.policlinico.model;

import jakarta.persistence.CascadeType;
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
import java.util.Set; // Preferible para colecciones donde el orden no importa y los elementos son únicos

@Entity
@Table(name = "Medico")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MED_ID")
    private Long medicoId;

    @Column(name = "MED_Nombres", nullable = false, length = 100) // Añadido length para buenas prácticas
    private String MED_Nombres;

    @Column(name = "MED_Apellidos", nullable = false, length = 100) // Añadido length
    private String MED_Apellidos;

    @Column(name = "MED_CMP", nullable = false, unique = true, length = 20) // Es tu número de colegiado
    private String MED_CMP;


    // 'Set' es mejor si un médico no puede tener dos horarios exactamente iguales (misma hora de inicio y fin, mismo día, misma fecha).
    // Si el orden de los horarios no importa y no quieres duplicados lógicos, 'Set' es más apropiado.
    @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<HorarioMedico> horariosMedico; // Renombrado para mayor claridad

    // Otras relaciones que tienes:
    @OneToMany(mappedBy = "medico")
    private List<Cita> citas;

    @OneToMany(mappedBy = "medico")
    private List<Historial_Medico> historialMedico;

    @OneToMany(mappedBy = "medico")
    private List<MedicoEspecialidad> medicoEspecialidades;

}


