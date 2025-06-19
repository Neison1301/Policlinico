package com.polyclinicapp.policlinico.controller;

import com.polyclinicapp.policlinico.Components.DatosTabla;
import com.polyclinicapp.policlinico.model.Paciente;
import com.polyclinicapp.policlinico.model.UsuarioSistema;
import com.polyclinicapp.policlinico.model.dto.MedicoDTO;
import com.polyclinicapp.policlinico.model.dto.TablaDTO;
import com.polyclinicapp.policlinico.service.interfaces.IServicioMedico;
import com.polyclinicapp.policlinico.service.interfaces.IServicioPaciente;
import com.polyclinicapp.policlinico.service.interfaces.IServicioUsuarioSistema;
import com.polyclinicapp.policlinico.repository.RepositorioPerfilUsuario;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor // Lombok para constructor con todos los args inyectados
@RequestMapping("/admin")
public class AdminController {

    private final IServicioUsuarioSistema usuarioSistemaService;
    private final RepositorioPerfilUsuario perfilUsuarioRepository;
    private final IServicioPaciente servicioPaciente;
    private final IServicioMedico servicioMedico;
    private final DatosTabla datosTabla;

    @GetMapping("/gestion-pacientes")
    public String gestionPacientes(Authentication auth, Model model, HttpServletRequest request) {
        datosTabla.setCommonAdminModelAttributes(auth, model, request, "Pacientes en el Sistema",
                "Gestion de Pacientes", "");

        List<Paciente> pacientes = servicioPaciente.findAllPacientes();
        TablaDTO datosPacientes = datosTabla.preparePacientesTableData(pacientes); // Este método ya define actions y rowIdField
        datosTabla.addTableDataToModel(model, datosPacientes); // Esto añade TODOS los atributos de TablaDTO al modelo

        return "dashboard/admin/gestionPaciente";
    }

    @GetMapping("gestion-medicos")
    public String gestionMedicos(Authentication auth, Model model, HttpServletRequest request) {
        datosTabla.setCommonAdminModelAttributes(auth, model, request, "Médicos en el Sistema", "Gestión de Médicos", "");

        List<MedicoDTO> medicosDTO = servicioMedico.findAllMedicos();
        TablaDTO medicosTableData = datosTabla.prepareMedicosTableData(medicosDTO); // Este método ya define actions y rowIdField
        datosTabla.addTableDataToModel(model, medicosTableData); // Esto añade TODOS los atributos de TablaDTO al modelo

        return "dashboard/admin/gestionMedico";
    }

    @GetMapping("/medicos/editar-horario/{id}")
    public String editarHorarioMedico(@PathVariable("id") Long id, Model model, Authentication auth,
                                      HttpServletRequest request) {
        Optional<MedicoDTO> medicoOptional = servicioMedico.findMedicoById(id);

        if (medicoOptional.isEmpty()) {
            return "redirect:/admin/gestion-medicos?error=medicoNotFound";
        }

        MedicoDTO medico = medicoOptional.get();
        model.addAttribute("medico", medico);

        datosTabla.setCommonAdminModelAttributes(auth, model, request,
                "Editar Horario", "Horario del Médico " + medico.getNombres() + " " + medico.getApellidos(), "");

        return "editar-horario";
    }

    @GetMapping("/gestion-personal")
    public String gestionRecepcionistas(Authentication auth, Model model, HttpServletRequest request) {
        datosTabla.setCommonAdminModelAttributes(auth, model, request, "Gestión de Recepcionistas",
                "Gestión de Recepcionistas", "");

        List<UsuarioSistema> todosLosUsuarios = usuarioSistemaService.findAllUsuarios();
        List<UsuarioSistema> recepcionistas = todosLosUsuarios.stream()
                .filter(u -> u.getRolNombre().equals("RECEPCIONISTA"))
                .collect(Collectors.toList());

        TablaDTO recepcionistasTableData = datosTabla.prepareRecepcionistasTableData(recepcionistas); // Este método ya define actions y rowIdField
        datosTabla.addTableDataToModel(model, recepcionistasTableData); // Esto añade TODOS los atributos de TablaDTO al modelo

        return "dashboard/admin/gestionPersonal";
    }

    @GetMapping("/perfil-admin")
    public String adminPerfil(Authentication auth, Model model, HttpServletRequest request) {
        datosTabla.setCommonAdminModelAttributes(auth, model, request, "Perfil de Administrador",
                "Perfil de Administrador", "fragments/admin/perfil-admin :: content");

        String username = auth.getName();
        usuarioSistemaService.findByUsuUsuario(username).ifPresent(usuarioSistema -> {
            perfilUsuarioRepository.findByUsuarioSistemaUsuId(usuarioSistema.getUsuId()).ifPresent(perfilUsuario -> {
                model.addAttribute("usuarioSistema", usuarioSistema);
                model.addAttribute("perfilUsuario", perfilUsuario);
            });
        });
        return "dashboard/admin/perfil-admin";
    }

    @GetMapping("/administracion-sistema")
    public String adminSistema(Authentication auth, Model model, HttpServletRequest request) {
        datosTabla.setCommonAdminModelAttributes(auth, model, request,
                "Administración del Sistema",
                "Configuración y Gestión de Usuarios",
                "");

        List<UsuarioSistema> todosLosUsuarios = usuarioSistemaService.findAllUsuarios();

        // Headers visibles para la tabla de usuarios
        List<String> displayHeaders = Arrays.asList("ID", "Nombre de Usuario", "Rol");
        // Claves de los campos en el mapa, en el mismo orden que los headers para el fragmento
        List<String> columnKeys = Arrays.asList("id", "usuario", "rol");

        List<Map<String, Object>> usuariosRowsAsMaps = todosLosUsuarios.stream().map(u -> {
            Map<String, Object> rowMap = new LinkedHashMap<>();
            rowMap.put("id", u.getUsuId());
            rowMap.put("usuario", u.getUsuUsuario());
            rowMap.put("rol", u.getRolNombre().replace("ROLE_", "")); // Quitar prefijo ROLE_ para mostrar
            return rowMap;
        }).collect(Collectors.toList());

        TablaDTO datosUsuariosSistema = new TablaDTO(
                "Usuarios del Sistema",
                displayHeaders,
                columnKeys, // Pasar las claves de las columnas
                usuariosRowsAsMaps,
                "id", // La clave para obtener el ID de la fila en el mapa
                Arrays.asList("view", "edit", "delete")
        );
        datosTabla.addTableDataToModel(model, datosUsuariosSistema);

        return "dashboard/admin/administracion-sistema";
    }
}