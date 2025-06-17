package com.polyclinicapp.policlinico.Components;

import com.polyclinicapp.policlinico.model.Paciente;
import com.polyclinicapp.policlinico.model.dto.RegistroPacienteDTO;
import org.springframework.stereotype.Component;

/*transforma un registroPacienteDto a la entidad paciente su unica funcion es mapear objetos */ 
@Component // Lo marco como componente de Spring para que lo detecte
public class PacienteMapper {

    public Paciente toEntity(RegistroPacienteDTO dto) {
        if (dto == null) {
            return null; // Si no hay DTO, no hay paciente que mapear
        }
        Paciente paciente = new Paciente();
        // Mapeo los campos del DTO a la entidad Paciente
        paciente.setPacDni(dto.getNumeroDocumento());
        paciente.setPacNombres(dto.getNombres());
        paciente.setPacApellidoPaterno(dto.getApellidoPaterno());
        paciente.setPacApellidoMaterno(dto.getApellidoMaterno());
        paciente.setPacFechanacimiento(dto.getFechaNacimiento());
        paciente.setPacSexo(dto.getSexo());
        paciente.setPacCorreo(dto.getCorreoElectronico());
        paciente.setPacTelefono(dto.getCelular());
        paciente.setPacAceptaTerminos(dto.isAceptaTerminos());
        
        return paciente; // Devuelvo la entidad Paciente ya mapeada
    }
}