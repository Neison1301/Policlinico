package com.polyclinicapp.policlinico.service.interfaces;

import java.util.List;

import com.polyclinicapp.policlinico.model.Medico;

public interface IServicioMedico {
    
    List<Medico> findAllMedicos();
}
