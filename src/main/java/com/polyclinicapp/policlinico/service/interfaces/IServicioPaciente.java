package com.polyclinicapp.policlinico.service.interfaces;

import java.util.List;

import com.polyclinicapp.policlinico.model.Paciente;
import com.polyclinicapp.policlinico.model.dto.RegistroPacienteDTO;

/**
 * Interfaz de servicio para la gestión de pacientes.
 * Define el contrato de las operaciones de negocio relacionadas con los
 * pacientes.
 * Sigue el principio D y O  (abierto a
 * extensión, cerrado a modificación).
 */
public interface IServicioPaciente {
        void registrarNuevoPaciente(RegistroPacienteDTO registroDTO) throws IllegalArgumentException;
        List<Paciente> findAllPacientes();

}
