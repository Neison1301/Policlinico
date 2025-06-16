package com.polyclinicapp.policlinico.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistroPersonalDTO {
    private String username;
    private String password;
    private String email;
    private String rolNombre; 
    private String tipoPerfil;

}
