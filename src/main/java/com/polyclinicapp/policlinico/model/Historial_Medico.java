package com.polyclinicapp.policlinico.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "Historial_Medico")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Historial_Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HIST_ID")
    private Long HIST_ID;

    @ManyToOne
    @JoinColumn(name = "PAC_ID", nullable = false)
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "MED_ID", nullable = false)
    private Medico medico;

    @Column(name = "HIST_Fecha", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date HIST_Fecha;

    @Column(name = "HIST_Diagnostico")
    private String HIST_Diagnostico;

    @Column(name = "HIST_Tratamiento")
    private String HIST_Tratamiento;
}