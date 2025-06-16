package com.polyclinicapp.policlinico.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "Cita")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CT_ID")
    private Long CT_ID;

    @ManyToOne
    @JoinColumn(name = "PAC_ID", nullable = false)
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "MED_ID", nullable = false)
    private Medico medico;

    @Column(name = "CT_Fecha", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date CT_Fecha;

    @Column(name = "CT_Hora", nullable = false)
    private LocalTime CT_Hora; // Se usa LocalTime para la hora

    @Column(name = "CT_Estado")
    private String CT_Estado;

    @OneToOne(mappedBy = "cita")
    private Notificacion notificacion;

  
}