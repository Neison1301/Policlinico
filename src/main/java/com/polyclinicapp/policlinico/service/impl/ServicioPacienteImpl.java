package com.polyclinicapp.policlinico.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.polyclinicapp.policlinico.Components.PacienteMapper;
import com.polyclinicapp.policlinico.model.Paciente;
import com.polyclinicapp.policlinico.model.UsuarioSistema;
import com.polyclinicapp.policlinico.model.dto.RegistroPacienteDTO;
import com.polyclinicapp.policlinico.repository.RepositorioPaciente;
import com.polyclinicapp.policlinico.repository.RepositorioUsuario;
import com.polyclinicapp.policlinico.service.interfaces.IServicioPaciente;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ServicioPacienteImpl implements IServicioPaciente {

    private final RepositorioPaciente repositorioPaciente;
    private final RepositorioUsuario repositorioUsuario;
    private final PasswordEncoder passwordEncoder;
    private final PacienteMapper pacienteMapper;

    @Override
    @Transactional
    public void registrarNuevoPaciente(RegistroPacienteDTO registroDTO) throws IllegalArgumentException {
        validarRegistroPaciente(registroDTO);

        UsuarioSistema nuevoUsuarioSistema = new UsuarioSistema();
        nuevoUsuarioSistema.setUsuUsuario(registroDTO.getNumeroDocumento());
        nuevoUsuarioSistema.setUsuContrasena(passwordEncoder.encode(registroDTO.getContrasena()));
        nuevoUsuarioSistema.setRolNombre("PACIENTE");
        nuevoUsuarioSistema = repositorioUsuario.save(nuevoUsuarioSistema);

        Paciente nuevoPaciente = pacienteMapper.toEntity(registroDTO);
        nuevoPaciente.setUsuarioSistema(nuevoUsuarioSistema);

        repositorioPaciente.save(nuevoPaciente);

    }

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
}