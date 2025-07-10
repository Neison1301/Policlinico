package com.polyclinicapp.policlinico.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecepcionistaDTO {
    private Long id;            // ID del recepcionista
    private String nombres;     // Nombres del recepcionista
    private String apellidos;   // Apellidos del recepcionista
    private String dni;         // Documento de identidad
    private String telefono;
    private String correo;
}
