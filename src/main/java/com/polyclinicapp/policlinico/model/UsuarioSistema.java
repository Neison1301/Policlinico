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
@Table(name = "UsuarioSistema")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioSistema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USU_ID")
    private Long USU_ID;

    @Column(name = "USU_Usuario", nullable = false, unique = true)
    private String USU_Usuario;

    @Column(name = "USU_Contrasena", nullable = false)
    private String USU_Contrasena;

    @ManyToOne
    @JoinColumn(name = "ROL_ID", nullable = false)
    private Rol rol;
}