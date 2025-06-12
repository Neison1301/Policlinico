package com.polyclinicapp.policlinico.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "Notificacion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NOTI_ID")
    private Long NOTI_ID;

    @OneToOne
    @JoinColumn(name = "CT_ID", referencedColumnName = "CT_ID", nullable = false)
    private Cita cita;

    @Column(name = "NOTI_Mensaje", nullable = false)
    private String NOTI_Mensaje;

    @Column(name = "NOTI_FechaEnvio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date NOTI_FechaEnvio;
}
