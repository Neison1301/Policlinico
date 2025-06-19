package com.polyclinicapp.policlinico.Components;

import com.polyclinicapp.policlinico.model.Paciente;
import com.polyclinicapp.policlinico.model.UsuarioSistema;
import com.polyclinicapp.policlinico.model.dto.MedicoDTO;
import com.polyclinicapp.policlinico.model.dto.TablaDTO;
import com.polyclinicapp.policlinico.service.interfaces.IServicioUsuarioSistema;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

import org.springframework.security.core.Authentication;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
public class DatosTabla {
    private final IServicioUsuarioSistema usuarioSistemaService;

    public TablaDTO preparePacientesTableData(List<Paciente> pacientes) {
        // Headers visibles
        List<String> displayHeaders = Arrays.asList("Nombres", "Apellidos", "F. de Nacimiento", "Teléfono", "Correo", "Sexo");
        // Claves del mapa correspondientes a los headers, en el mismo orden
        List<String> columnKeys = Arrays.asList("nombres", "apellidos", "fechaNacimiento", "telefono", "correo", "sexo");

        List<Map<String, Object>> rowsAsMaps = new ArrayList<>();
        for (Paciente p : pacientes) {
            Map<String, Object> rowMap = new LinkedHashMap<>();
            rowMap.put("id", p.getPacId()); // Esta clave "id" es para rowIdField
            rowMap.put("nombres", p.getPacNombres());
            rowMap.put("apellidos", p.getPacApellidoPaterno() + " " + p.getPacApellidoMaterno());
            rowMap.put("fechaNacimiento", p.getPacFechanacimiento() != null ? p.getPacFechanacimiento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "N/A");
            rowMap.put("telefono", p.getPacTelefono());
            rowMap.put("correo", p.getPacCorreo());
            rowMap.put("sexo", p.getPacSexo());
            rowsAsMaps.add(rowMap);
        }

        return new TablaDTO(
                "Pacientes en el Sistema",
                displayHeaders,
                columnKeys, // Pasar las claves de las columnas
                rowsAsMaps,
                "id", // El nombre de la clave del mapa que contiene el ID de la fila
                Arrays.asList("view", "edit", "delete"));
    }

    public TablaDTO prepareMedicosTableData(List<MedicoDTO> medicosDTO) {
        List<String> displayHeaders = Arrays.asList("Nombres", "Apellidos", "CMP");
        List<String> columnKeys = Arrays.asList("nombres", "apellidos", "cmp"); // Claves del mapa para médicos

        List<Map<String, Object>> rowsAsMaps = new ArrayList<>();
        for (MedicoDTO m : medicosDTO) {
            Map<String, Object> rowMap = new LinkedHashMap<>();
            rowMap.put("id", m.getId());
            rowMap.put("nombres", m.getNombres());
            rowMap.put("apellidos", m.getApellidos());
            rowMap.put("cmp", m.getCmp());
            rowsAsMaps.add(rowMap);
        }

        return new TablaDTO(
                "Médicos en el Sistema",
                displayHeaders,
                columnKeys, // Pasar las claves de las columnas
                rowsAsMaps,
                "id",
                Arrays.asList("view", "edit", "delete", "edit_horario"));
    }

    public TablaDTO prepareRecepcionistasTableData(List<UsuarioSistema> recepcionistas) {
        List<String> displayHeaders = Arrays.asList("Usuario", "Rol");
        List<String> columnKeys = Arrays.asList("usuario", "rol"); // Claves del mapa para recepcionistas

        List<Map<String, Object>> rowsAsMaps = new ArrayList<>();
        for (UsuarioSistema u : recepcionistas) {
            Map<String, Object> rowMap = new LinkedHashMap<>();
            rowMap.put("id", u.getUsuId());
            rowMap.put("usuario", u.getUsuUsuario());
            rowMap.put("rol", u.getRolNombre().replace("ROLE_", "")); // Limpiar el prefijo ROLE_ para mostrar
            rowsAsMaps.add(rowMap);
        }

        return new TablaDTO(
                "Recepcionistas del Policlínico",
                displayHeaders,
                columnKeys, // Pasar las claves de las columnas
                rowsAsMaps,
                "id",
                Arrays.asList("view", "edit", "delete"));
    }

    // Este método ya no es un fragmento dinámico. Ahora se encarga de añadir los atributos al modelo.
    public void addTableDataToModel(Model model, TablaDTO tableData) {
        model.addAttribute("tableTitle", tableData.getTitle());
        model.addAttribute("tableHeaders", tableData.getHeaders());
        model.addAttribute("tableColumnKeys", tableData.getColumnKeys()); // Añadir las claves de las columnas al modelo
        model.addAttribute("tableRows", tableData.getRows());
        model.addAttribute("tableRowIdField", tableData.getRowIdField());
        model.addAttribute("tableActions", tableData.getActions());
    }

    public void setCommonAdminModelAttributes(Authentication auth, Model model, HttpServletRequest request,
                                                String pageTitle, String headerTitle, String contentFragment) {
        String username = auth.getName();
        usuarioSistemaService.findByUsuUsuario(username).ifPresent(usuarioSistema -> {
            String tipoPerfil = usuarioSistema.getRolNombre().replace("ROLE_", "");
            model.addAttribute("tipoPerfil", tipoPerfil);
        });
        model.addAttribute("currentUri", request.getRequestURI());
        model.addAttribute("pageTitle", pageTitle);
        model.addAttribute("headerTitle", headerTitle);
        // contentFragment ya no se usa directamente para cargar el fragmento th:replace,
        // se asume que la vista lo sabe. Si aún lo necesitas, puedes añadirlo.
        // model.addAttribute("contentFragment", contentFragment);
    }
}