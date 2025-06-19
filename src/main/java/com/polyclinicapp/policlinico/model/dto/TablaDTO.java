package com.polyclinicapp.policlinico.model.dto;

import java.util.List;
import java.util.Map; // Asegúrate de importar Map

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TablaDTO {
    private String title;
    private List<String> headers; // Para los encabezados visibles en la tabla (ej. "Nombre de Usuario")
    private List<String> columnKeys; // NUEVO: Para las claves internas de cada columna en el mapa (ej. "nombreDeUsuario")
    private List<Map<String, Object>> rows;
    private String rowIdField;
    private List<String> actions;
}