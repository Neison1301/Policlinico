
package com.polyclinicapp.policlinico.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicoDTO {
    private Long id; // Corresponde a MED_ID
    private String nombres; // Corresponde a MED_Nombres
    private String apellidos; // Corresponde a MED_Apellidos
    private String cmp; // Corresponde a MED_CMP (Número de colegiado)
    // Ya NO se incluye horarioAtencion aquí
    private List<HorarioMedicoDTO> horariosMedico; // Solo si se necesita para alguna operación específica
}