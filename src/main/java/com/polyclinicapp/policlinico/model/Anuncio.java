package com.polyclinicapp.policlinico.model; // Asegúrate de ajustar el paquete si es necesario

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date; 

@Entity
@Table(name = "Anuncio") 
@Data 
@NoArgsConstructor 
@AllArgsConstructor 
public class Anuncio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ANUNCIO_ID")
    private Long id;

    @Column(name = "TITULO", nullable = false, length = 255) 
    private String titulo;

    @Column(name = "CONTENIDO", columnDefinition = "TEXT") 
    private String contenido;

    @Column(name = "ANUNCIO_FECHA", nullable = false) 
    @Temporal(TemporalType.DATE)
    private Date fechaDePublicacion; 
}