package com.polyclinicapp.policlinico.Components;

import com.polyclinicapp.policlinico.model.Paciente;
import com.polyclinicapp.policlinico.model.dto.RegistroPacienteDTO;
import org.springframework.stereotype.Component;


@Component // Indica a Spring que es un componente y puede ser inyectado
public class PacienteMapper {

    public Paciente toEntity(RegistroPacienteDTO dto) {
        if (dto == null) {
            return null;
        }
        Paciente paciente = new Paciente();
        paciente.setPacDni(dto.getNumeroDocumento());
        paciente.setPacNombres(dto.getNombres());
        paciente.setPacApellidoPaterno(dto.getApellidoPaterno());
        paciente.setPacApellidoMaterno(dto.getApellidoMaterno());
        paciente.setPacFechanacimiento(dto.getFechaNacimiento());
        paciente.setPacSexo(dto.getSexo());
        paciente.setPacCorreo(dto.getCorreoElectronico());
        paciente.setPacTelefono(dto.getCelular());
        paciente.setPacAceptaTerminos(dto.isAceptaTerminos());
        
        
        return paciente;
    }
}