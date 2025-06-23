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
    private List<String> headers; 
    private List<String> columnKeys; 
    private List<Map<String, Object>> rows;
    private String rowIdField;
    private List<String> actions;
}