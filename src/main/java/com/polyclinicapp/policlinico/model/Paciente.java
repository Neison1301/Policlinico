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

import java.time.LocalDate;
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
    private Long pacId; // Cambiado de PAC_ID a pacId

    @Column(name = "PAC_DNI", nullable = false, unique = true)
    private String pacDni; // Ya lo habías cambiado, ¡perfecto!

    @Column(name = "PAC_Nombres", nullable = false)
    private String pacNombres; // Cambiado de PAC_Nombres a pacNombres

    @Column(name = "PAC_ApellidoPaterno", nullable = false)
    private String pacApellidoPaterno; // Cambiado de PAC_ApellidoPaterno a pacApellidoPaterno

    @Column(name = "PAC_ApellidoMaterno", nullable = false)
    private String pacApellidoMaterno; // Cambiado de PAC_apellidoMaterno a pacApellidoMaterno

    @Column(name = "PAC_Fechanacimiento")
    @Temporal(TemporalType.DATE)
    private LocalDate pacFechanacimiento; // Cambiado de PAC_Fechanacimiento a pacFechanacimiento

    @Column(name = "PAC_Telefono")
    private String pacTelefono; // Cambiado de PAC_Telefono a pacTelefono

    @Column(name = "PAC_Sexo")
    private String pacSexo; // Cambiado de PAC_sexo a pacSexo

    @Column(name = "PAC_Correo")
    private String pacCorreo; // Cambiado de PAC_Correo a pacCorreo

    @Column(name = "PAC_aceptaTerminos")
    private Boolean pacAceptaTerminos; // Cambiado de PAC_aceptaTerminos a pacAceptaTerminos

    @ManyToOne
    @JoinColumn(name = "usu_id", referencedColumnName = "usu_id")
    private UsuarioSistema usuarioSistema;

    @OneToMany(mappedBy = "paciente")
    private List<Cita> citas;

    @OneToMany(mappedBy = "paciente")
    private List<Historial_Medico> historialMedico;

}