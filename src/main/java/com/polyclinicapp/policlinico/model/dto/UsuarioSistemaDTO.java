// src/main/java/com/polyclinicapp.policlinico.model.dto/UsuarioSistemaDTO.java
package com.polyclinicapp.policlinico.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioSistemaDTO {
    private Long id;
    private String usuario;
    private String rolNombre;
    private String estado;
}