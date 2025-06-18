// Archivo: src/main/java/com/polyclinicapp/policlinico/service/impl/ServicioMedicoImpl.java
package com.polyclinicapp.policlinico.service.impl; // <--- Este es el paquete correcto

import com.polyclinicapp.policlinico.model.Medico;
import com.polyclinicapp.policlinico.repository.RepositorioMedico; // Asume que tienes este repositorio
import com.polyclinicapp.policlinico.service.interfaces.IServicioMedico;
import org.springframework.stereotype.Service; // ¡Importante!

import java.util.List;
import java.util.Optional;

@Service // ¡Esta anotación es indispensable! Le dice a Spring que esta clase es un bean.
public class ServicioMedicoImpl implements IServicioMedico {

    private final RepositorioMedico repositorioMedico;

    // Inyección por constructor del repositorio
    public ServicioMedicoImpl(RepositorioMedico repositorioMedico) {
        this.repositorioMedico = repositorioMedico;
    }

    @Override
    public List<Medico> findAllMedicos() {
        // Implementación: llama al método findAll() de tu repositorio
        return repositorioMedico.findAll();
    }
    // Si IServicioMedico tiene más métodos, asegúrate de implementarlos aquí
}