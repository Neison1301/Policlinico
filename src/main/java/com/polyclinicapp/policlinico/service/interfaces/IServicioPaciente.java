package com.polyclinicapp.policlinico.service.interfaces;

import com.polyclinicapp.policlinico.model.dto.RegistroPacienteDTO;

public interface IServicioPaciente {
        void registrarNuevoPaciente(RegistroPacienteDTO registroDTO) throws IllegalArgumentException;

}
