package com.polyclinicapp.policlinico.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Recepcionista")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recepcionista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REC_ID")
    private Long recId;

    @Column(name = "REC_DNI", nullable = false, unique = true)
    private String recDni;

    @Column(name = "REC_Nombres", nullable = false, length = 100)
    private String recNombres;

    @Column(name = "REC_Apellidos", nullable = false, length = 100)
    private String recApellidos;

    @Column(name = "REC_Telefono")
    private String recTelefono;

    @Column(name = "REC_Correo")
    private String recCorreo;

    @ManyToOne
    @JoinColumn(name = "usu_id", referencedColumnName = "usu_id")
    private UsuarioSistema usuarioSistema;

    // Relación con citas registradas por esta persona
    @OneToMany(mappedBy = "recepcionista")
    private List<Cita> citasRegistradas;
}
