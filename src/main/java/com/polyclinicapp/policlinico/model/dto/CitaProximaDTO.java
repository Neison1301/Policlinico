package com.polyclinicapp.policlinico.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CitaProximaDTO {
    private Date fecha;
    private LocalTime hora;
    private MedicoBasicInfoDTO medico;
    private String estado;
}