package com.example.pruebatecnicajava.DTO;

import com.example.pruebatecnicajava.entity.StatusUsuario;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsuarioResponse {
    private String login;
    private String nombre;
    private Float cliente;
    private String email;
    private LocalDate fechaAlta;
    private LocalDate fechaBaja;
    private StatusUsuario status;
    private Float intentos;
    private LocalDate fechaRevocado;
    private LocalDate fechaVigencia;
    private Integer noAcceso;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private Integer area;
    private LocalDate fechaModificacion;
}
