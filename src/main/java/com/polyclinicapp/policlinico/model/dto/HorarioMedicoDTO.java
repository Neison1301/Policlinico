package com.polyclinicapp.policlinico.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HorarioMedicoDTO {
    private Long id; // Corresponde a HM_ID (para identificar el horario al actualizar/eliminar)
    private Long medicoId; // Para asociar al médico (necesario al crear o consultar horarios de un médico)
    private DayOfWeek diaSemana; // Corresponde a HM_DiaSemana
    private LocalTime horaInicio; // Corresponde a HM_HoraInicio
    private LocalTime horaFin; // Corresponde a HM_HoraFin
    private LocalDate fechaEspecifica; // Corresponde a HM_FechaEspecifica (puede ser nulo para horarios recurrentes)
    private String tipoHorario; // Corresponde a HM_TipoHorario (ej. "DISPONIBLE", "CONSULTA")
}