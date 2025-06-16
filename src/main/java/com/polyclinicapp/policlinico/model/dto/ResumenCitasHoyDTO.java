package com.polyclinicapp.policlinico.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResumenCitasHoyDTO {
    private long totalCitas;
    private long confirmadas;
    private long pendientes;
}