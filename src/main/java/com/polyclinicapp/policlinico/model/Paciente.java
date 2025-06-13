package com.polyclinicapp.policlinico.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Paciente")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PAC_ID")
    private Long PAC_ID;

    @Column(name = "PAC_DNI", nullable = false, unique = true)
    private String PAC_DNI;

    @Column(name = "PAC_Nombres", nullable = false)
    private String PAC_Nombres;

    @Column(name = "PAC_Apellidos", nullable = false)
    private String PAC_Apellidos;

    @Column(name = "PAC_Fechanacimiento")
    @Temporal(TemporalType.DATE)
    private Date PAC_Fechanacimiento;

    @Column(name = "PAC_Telefono")
    private String PAC_Telefono;

    @Column(name = "PAC_Correo")
    private String PAC_Correo;

    @ManyToOne
    @JoinColumn(name = "usu_id", referencedColumnName = "usu_id")
    private UsuarioSistema usuarioSistema;

    @OneToMany(mappedBy = "paciente")
    private List<Cita> citas;

    @OneToMany(mappedBy = "paciente")
    private List<Historial_Medico> historialMedico;
}
