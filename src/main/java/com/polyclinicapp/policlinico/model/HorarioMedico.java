package com.polyclinicapp.policlinico.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.EnumType; // Para DayOfWeek
import jakarta.persistence.Enumerated; // Para DayOfWeek
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek; // Enum para los días de la semana
import java.time.LocalTime; // Para representar solo la hora (HH:MM:SS)
import java.time.LocalDate; // Para fechas específicas si se necesita (ej. vacaciones)

@Entity
@Table(name = "Horario_Medico") // Nombre de tabla con tu convención
@Data // Genera getters, setters, toString, equals, hashCode
@NoArgsConstructor // Genera constructor sin argumentos
@AllArgsConstructor // Genera constructor con todos los argumentos
public class HorarioMedico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HM_ID") // ID del horario
    private Long HM_ID;

    @ManyToOne // Un HorarioMedico pertenece a un solo Medico
    @JoinColumn(name = "MED_ID", nullable = false) // Columna de clave foránea en la tabla Horario_Medico
    private Medico medico; // Referencia al médico al que pertenece este horario

    @Column(name = "HM_DiaSemana", nullable = false)
    @Enumerated(EnumType.STRING) // Guarda el nombre del enum (ej. "MONDAY") en la DB
    private DayOfWeek diaSemana;

    @Column(name = "HM_HoraInicio", nullable = false)
    private LocalTime horaInicio;

    @Column(name = "HM_HoraFin", nullable = false)
    private LocalTime horaFin;

    @Column(name = "HM_FechaEspecifica") // Campo opcional para horarios de un día concreto (vacaciones, eventos)
    private LocalDate fechaEspecifica;

    @Column(name = "HM_TipoHorario", length = 50) // Ej: "DISPONIBLE", "DESCANSO", "CONSULTA"
    private String tipoHorario;
}