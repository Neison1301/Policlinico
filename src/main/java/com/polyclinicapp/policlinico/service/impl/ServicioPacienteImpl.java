package com.polyclinicapp.policlinico.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.polyclinicapp.policlinico.Components.PacienteMapper;
import com.polyclinicapp.policlinico.model.Paciente;
import com.polyclinicapp.policlinico.model.UsuarioSistema;
import com.polyclinicapp.policlinico.model.dto.RegistroPacienteDTO;
import com.polyclinicapp.policlinico.repository.RepositorioPaciente;
import com.polyclinicapp.policlinico.repository.RepositorioUsuario;
import com.polyclinicapp.policlinico.service.interfaces.IServicioPaciente;
import com.polyclinicapp.policlinico.service.interfaces.IServicioUsuarioSistema;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

/*implementacion de la interfaz iserviiopaciente 
contiene la logica de negocio para la gestion de paciente y usa S y D */
@Service
@AllArgsConstructor
public class ServicioPacienteImpl implements IServicioPaciente {

    private final RepositorioPaciente repositorioPaciente;
    private final RepositorioUsuario repositorioUsuario;
    private final IServicioUsuarioSistema usuarioSistemaService;
    private final PacienteMapper pacienteMapper;

    @Override
    @Transactional
    public void registrarNuevoPaciente(RegistroPacienteDTO registroDTO) throws IllegalArgumentException {
        validarRegistroPaciente(registroDTO);

        UsuarioSistema nuevoUsuarioSistema = usuarioSistemaService.registerNewUser(
                registroDTO.getNumeroDocumento(),
                registroDTO.getContrasena(),
                "PACIENTE",
                "Paciente");

        Paciente nuevoPaciente = pacienteMapper.toEntity(registroDTO);
        nuevoPaciente.setUsuarioSistema(nuevoUsuarioSistema);
        repositorioPaciente.save(nuevoPaciente);

    }

    // validaciones
    private void validarRegistroPaciente(RegistroPacienteDTO registroDTO) {
        if (!registroDTO.getContrasena().equals(registroDTO.getConfirmarContrasena())) {
            throw new IllegalArgumentException("Las contraseñas no coinciden.");
        }
        if (repositorioPaciente.findByPacCorreo(registroDTO.getCorreoElectronico()).isPresent()) {
            throw new IllegalArgumentException("El correo electrónico ya está registrado para un paciente.");
        }
        if (repositorioPaciente.findByPacDni(registroDTO.getNumeroDocumento()).isPresent()) {
            throw new IllegalArgumentException("El número de documento (DNI) ya está registrado para un paciente.");
        }
        if (repositorioUsuario.findByUsuUsuario(registroDTO.getNumeroDocumento()).isPresent()) {
            throw new IllegalArgumentException("Ya existe una cuenta de usuario con este número de documento (DNI).");
        }
        if (!registroDTO.isAceptaTerminos()) {
            throw new IllegalArgumentException("Debe aceptar los términos y condiciones para continuar.");
        }
    }

    @Override
    public List<Paciente> findAllPacientes() {
        return repositorioPaciente.findAll();
    }
}