package com.polyclinicapp.policlinico.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

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

    @Override
    public void eliminarPacientePorId(Long id) {
        // Buenas prácticas:
        // 1. Verificar si el paciente existe antes de intentar borrar.
        // Esto proporciona un mensaje de error más claro si el ID no es válido.
        if (!repositorioPaciente.existsById(id)) {
            throw new NoSuchElementException("Paciente con ID " + id + " no encontrado para eliminación.");
        }
        repositorioPaciente.deleteById(id);
        // Opcional: Si quieres recuperar el paciente para alguna auditoría antes de
        // borrarlo:
        // Paciente pacienteAEliminar = repositorioPaciente.findById(id)
        // .orElseThrow(() -> new NoSuchElementException("Paciente no encontrado"));
        // repositorioPaciente.delete(pacienteAEliminar);
    }

    @Override
    public void eliminarMultiplesPacientesPorIds(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            // Podrías lanzar una IllegalArgumentException o simplemente no hacer nada
            throw new IllegalArgumentException("La lista de IDs para eliminar no puede ser nula o vacía.");
        }
        // JpaRepository tiene un método deleteAllById para eliminar por una colección
        // de IDs.
        // Esto es más eficiente que eliminarlos uno por uno.
        repositorioPaciente.deleteAllById(ids);

        // Si usaste el método @Query en el repositorio:
        // repositorioPaciente.deleteAllByIdIn(ids);
    }

    // Opcional: Implementación de borrado lógico (si Paciente tiene un campo
    // 'activo')
    // @Override
    // @Transactional
    // public void desactivarPaciente(Long id) {
    // Paciente paciente = repositorioPaciente.findById(id)
    // .orElseThrow(() -> new NoSuchElementException("Paciente no encontrado."));
    // paciente.setActivo(false); // Asumiendo un campo 'activo'
    // repositorioPaciente.save(paciente);
    // }
}
