package com.polyclinicapp.policlinico.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecepcionistaBasicInfoDTO {
    private String nombreCompleto; // Combina nombres y apellidos
    private String correo;
    private String telefono;
}