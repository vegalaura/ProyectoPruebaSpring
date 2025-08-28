package com.example.pruebatecnicajava.DTO;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FiltrosRequest {
    LocalDate fechaAltaInicio;
    LocalDate fechaAltaFin;
    String nombre;
    String estatus;
}
