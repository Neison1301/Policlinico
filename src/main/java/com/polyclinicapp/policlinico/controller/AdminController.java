package com.polyclinicapp.policlinico.controller;

import com.polyclinicapp.policlinico.model.Medico; // Necesitarás tus entidades
import com.polyclinicapp.policlinico.model.Paciente;
import com.polyclinicapp.policlinico.model.UsuarioSistema;
import com.polyclinicapp.policlinico.service.interfaces.IServicioMedico; // y sus servicios
import com.polyclinicapp.policlinico.service.interfaces.IServicioPaciente;
import com.polyclinicapp.policlinico.service.interfaces.IServicioUsuarioSistema;
import com.polyclinicapp.policlinico.repository.RepositorioPerfilUsuario; // Si lo necesitas para el perfil

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin") // Todas las rutas aquí comenzarán con /admin
public class AdminController {

    private final IServicioUsuarioSistema usuarioSistemaService;
    private final RepositorioPerfilUsuario perfilUsuarioRepository; // Para cargar el perfil del admin
    private final IServicioPaciente servicioPaciente; // Para la gestión de pacientes
    private final IServicioMedico servicioMedico; // Para la gestión de médicos
    // private final IServicioPersonal servicioPersonal; // Y para el personal, si
    // lo tienes

    public AdminController(IServicioUsuarioSistema usuarioSistemaService,
            RepositorioPerfilUsuario perfilUsuarioRepository,
            IServicioPaciente servicioPaciente,
            IServicioMedico servicioMedico) { // Inyecta todos los servicios necesarios
        this.usuarioSistemaService = usuarioSistemaService;
        this.perfilUsuarioRepository = perfilUsuarioRepository;
        this.servicioPaciente = servicioPaciente;
        this.servicioMedico = servicioMedico;
    }

    /**
     * Punto de entrada al dashboard de administración.
     * Redirige a la gestión de pacientes por defecto o a una página de inicio
     * general.
     */
    @GetMapping("/dashboard")
    public String dashboardAdmin(Authentication auth, Model model, HttpServletRequest request) {
        // Llama a este método para establecer los atributos comunes, incluyendo el
        // "pageTitle" y "headerTitle"
        setCommonAdminModelAttributes(auth, model, request, "Dashboard Administrador", "Bienvenido al Dashboard", "");

        // Carga los datos de pacientes (o cualquier dato inicial que quieras mostrar en
        // el dashboard principal)
        List<Paciente> pacientes = servicioPaciente.findAllPacientes();

        List<List<String>> rows = pacientes.stream().map(p -> Arrays.asList(
                p.getPacNombres(),
                p.getPacApellidoPaterno() + " " + p.getPacApellidoMaterno(),
                p.getPacFechanacimiento() != null ? p.getPacFechanacimiento().toString() : "N/A",
                p.getPacTelefono(),
                p.getPacCorreo(),
                p.getPacSexo())).collect(Collectors.toList());

        model.addAttribute("tableTitle", "Pacientes"); // Necesitas definir tableTitle aquí
        model.addAttribute("tableHeaders",
                Arrays.asList("Nombres", "Apellidos", "F. de Nacimiento", "Teléfono", "Correo", "Sexo"));
        model.addAttribute("tableRows", rows);
        model.addAttribute("tableRowIdField", "pacId");
        model.addAttribute("tableActions", Arrays.asList("view", "edit", "delete"));

        // Retorna la vista que quieres mostrar cuando un ADMIN accede a
        // /admin/dashboard
        // Si gestionPaciente.html es la página que muestra la tabla, entonces está
        // bien.
        return "dashboard/admin/gestionPaciente";
    }

    /** Muestra la página de gestión de pacientes con la tabla dinámica. */
    @GetMapping("/gestion-pacientes")
    public String gestionPacientes(Authentication auth, Model model, HttpServletRequest request) {
        setCommonAdminModelAttributes(auth, model, request, "Pacientes en el Sistema", "Gestion de Pacientes", ""); // Último
                                                                                                                    // parámetro
                                                                                                                    // no
                                                                                                                    // usado
                                                                                                                    // con
                                                                                                                    // el
                                                                                                                    // nuevo
                                                                                                                    // enfoque

        // --- Preparar datos para la tabla de pacientes ---
        List<Paciente> pacientes = servicioPaciente.findAllPacientes();

        List<List<String>> rows = pacientes.stream().map(p -> Arrays.asList(
                p.getPacNombres(),
                p.getPacApellidoPaterno() + " " + p.getPacApellidoMaterno(),
                p.getPacFechanacimiento() != null ? p.getPacFechanacimiento().toString() : "N/A",
                p.getPacTelefono(),
                p.getPacCorreo(),
                p.getPacSexo())).collect(Collectors.toList());

        model.addAttribute("tableTitle", "Pacientes en el Sistema");
        model.addAttribute("tableHeaders",
                Arrays.asList("Nombres", "Apellidos", "F. de Nacimiento", "Teléfono", "Correo", "Sexo"));
        model.addAttribute("tableRows", rows);
        model.addAttribute("tableRowIdField", "pacId");
        model.addAttribute("tableActions", Arrays.asList("view", "edit", "delete"));

        return "dashboard/admin/gestionPaciente"; // Usamos un layout principal para todas las gestiones
    }

    /** Muestra la página de gestión de médicos con la tabla dinámica. */
    @GetMapping("/gestion-medicos")
    public String gestionMedicos(Authentication auth, Model model, HttpServletRequest request) {
        setCommonAdminModelAttributes(auth, model, request, "Médicos en el Sistema", "Gestion de Médicos", "");

        // --- Preparar datos para la tabla de médicos ---
        List<Medico> medicos = servicioMedico.findAllMedicos(); // Asume que tienes este método

        List<List<String>> rows = medicos.stream().map(m -> Arrays.asList(
                m.getMED_Nombres(),
                m.getMED_Apellidos(),
                m.getMED_CMP(),
                m.getMED_HorarioAtencion() != null ? m.getMED_HorarioAtencion() : "N/A")).collect(Collectors.toList());

        model.addAttribute("tableTitle", "Médicos en el Sistema");
        model.addAttribute("tableHeaders", Arrays.asList("Nombres", "Apellidos", "CMP", "Horario Atención"));
        model.addAttribute("tableRows", rows);
        model.addAttribute("tableRowIdField", "MED_ID");
        model.addAttribute("tableActions", Arrays.asList("view", "edit", "delete"));

        return "dashboard/admin/gestionMedico";
    }

    @GetMapping("/gestion-personal") // O /gestion-personal si prefieres ese nombre de ruta
    public String gestionRecepcionistas(Authentication auth, Model model, HttpServletRequest request) {
        setCommonAdminModelAttributes(auth, model, request, "Gestión de Recepcionistas", "Gestión de Recepcionistas",
                "");

        // Asumiendo que puedes obtener todos los usuarios y filtrar por rol
        List<UsuarioSistema> todosLosUsuarios = usuarioSistemaService.findAllUsuarios();
        // exista

        // Filtra solo los usuarios que tienen el rol de RECEPCIONISTA
        List<UsuarioSistema> recepcionistas = todosLosUsuarios.stream()
                .filter(u -> u.getRolNombre().equals("ROLE_RECEPCIONISTA")) // Asegúrate de que el nombre del rol es
                                                                            // correcto
                .collect(Collectors.toList());

        List<List<String>> rows = recepcionistas.stream().map(u -> Arrays.asList(
                String.valueOf(u.getUsuId()),
                u.getUsuUsuario(), // Nombre de usuario
                u.getRolNombre()// Mostrar el rol (que debería ser RECEPCIONISTA)
        )).collect(Collectors.toList());

        model.addAttribute("tableTitle", "Recepcionistas del Policlínico");
        model.addAttribute("tableHeaders", Arrays.asList("ID", "Usuario", "Rol"));
        model.addAttribute("tableRows", rows);
        model.addAttribute("tableRowIdField", "0"); // El ID está en la columna 0
        model.addAttribute("tableActions", Arrays.asList("view", "edit", "delete"));

        return "dashboard/admin/gestionPersonal"; // Retorna el layout principal
    }

    // ... Continúa con gestion-personal, administracion-sistema,
    // informes-estadisticas
    // Siguiendo el mismo patrón:
    // 1. Llama a setCommonAdminModelAttributes
    // 2. Carga los datos específicos
    // 3. Prepara tableTitle, tableHeaders, tableRows, tableRowIdField, tableActions
    // 4. Retorna "dashboard/admin/main-admin-layout"

    @GetMapping("/perfil-admin")
    public String adminPerfil(Authentication auth, Model model, HttpServletRequest request) {
        // En este caso, el `tableTitle` y `contentFragment` no aplican a un perfil,
        // pero podemos usar `pageTitle` y un fragmento específico para el perfil.
        setCommonAdminModelAttributes(auth, model, request, "Perfil de Administrador", "Perfil de Administrador",
                "fragments/admin/perfil-admin :: content");

        String username = auth.getName();
        usuarioSistemaService.findByUsuUsuario(username).ifPresent(usuarioSistema -> {
            perfilUsuarioRepository.findByUsuarioSistemaUsuId(usuarioSistema.getUsuId()).ifPresent(perfilUsuario -> {
                model.addAttribute("usuarioSistema", usuarioSistema);
                model.addAttribute("perfilUsuario", perfilUsuario);
            });
        });
        return "dashboard/admin/perfil-admin"; // Usamos el mismo layout principal
    }

    /**
     * Método utilitario para establecer atributos comunes del modelo para las
     * páginas de administración.
     * 
     * @param auth            Objeto de autenticación.
     * @param model           Objeto Model de Spring.
     * @param request         Objeto HttpServletRequest.
     * @param pageTitle       Título de la página (para el tag <title>).
     * @param headerTitle     Título que se mostrará en el encabezado del contenido.
     * @param contentFragment Nombre del fragmento HTML específico a incluir (vacío
     *                        si es la tabla dinámica).
     */
    private void setCommonAdminModelAttributes(Authentication auth, Model model, HttpServletRequest request,
            String pageTitle, String headerTitle, String contentFragment) {
        String username = auth.getName();
        usuarioSistemaService.findByUsuUsuario(username).ifPresent(usuarioSistema -> {
            String tipoPerfil = usuarioSistema.getRolNombre().replace("ROLE_", "");
            model.addAttribute("tipoPerfil", tipoPerfil);
        });
        model.addAttribute("currentUri", request.getRequestURI());
        model.addAttribute("pageTitle", pageTitle); // Título de la ventana/tab del navegador
        model.addAttribute("headerTitle", headerTitle); // Título grande dentro del contenido (como "Pacientes en el
                                                        // Sistema")
        // No usaremos contentFragment directamente en el layout principal si usamos la
        // tabla dinámica.
        // Pero podría usarse para otras secciones que no son tablas.
    }
}