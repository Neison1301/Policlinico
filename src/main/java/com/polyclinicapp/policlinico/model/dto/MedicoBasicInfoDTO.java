package com.polyclinicapp.policlinico.model.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List; // Necesitamos esto para la lista de especialidades

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicoBasicInfoDTO {
    private String nombreCompleto; // Unirá MED_Nombres y MED_Apellidos
    private List<EspecialidadBasicInfoDTO> especialidades; // Un médico puede tener varias especialidades
}