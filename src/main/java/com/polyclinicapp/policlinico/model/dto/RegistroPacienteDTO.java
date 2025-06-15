package com.polyclinicapp.policlinico.model.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data // Genera getters, setters, toString, equals y hashCode automáticamente
@NoArgsConstructor
@AllArgsConstructor
public class RegistroPacienteDTO {


    @NotBlank(message = "El número de documento es requerido")
    private String numeroDocumento;

    @NotBlank(message = "Los nombres son requeridos")
    private String nombres;

    @NotBlank(message = "El apellido paterno es requerido")
    private String apellidoPaterno;
    @NotBlank(message = "El apellido materno es requerido")
    private String apellidoMaterno;

    @NotNull(message = "La fecha de nacimiento es requerida")
    @Past(message = "La fecha de nacimiento debe ser en el pasado")
    private LocalDate fechaNacimiento;

    @NotBlank(message = "El sexo es requerido")
    private String sexo;

    @NotBlank(message = "El correo electrónico es requerido")
    @Email(message = "El correo electrónico no es válido")
    private String correoElectronico;

    private String celular;

    @NotBlank(message = "La contraseña es requerida")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    private String contrasena;

    @NotBlank(message = "La confirmación de contraseña es requerida")
    private String confirmarContrasena;
    
    @NotNull(message = "Debe aceptar los términos y condiciones.")
    private boolean aceptaTerminos;


}