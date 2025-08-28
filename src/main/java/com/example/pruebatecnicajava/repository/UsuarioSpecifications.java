package com.example.pruebatecnicajava.repository;

import com.example.pruebatecnicajava.entity.Usuario;
import java.time.LocalDate;
import org.springframework.data.jpa.domain.Specification;

public class UsuarioSpecifications {
    public static Specification<Usuario> conNombre(String nombre) {
        return (root, query, cb) ->
            (nombre == null || nombre.isEmpty()) ? null : cb.like(cb.lower(root.get("nombre")), "%" + nombre.toLowerCase() + "%");
    }

    public static Specification<Usuario> conEstatus(String estatus) {
        return (root, query, cb) ->
            estatus == null || estatus.isEmpty() ? null : cb.equal(root.get("status"), estatus);
    }

    public static Specification<Usuario> conFechaAltaEntre(LocalDate inicio, LocalDate fin) {
        return (root, query, cb) -> {
            if (inicio != null && fin != null) {
                return cb.between(root.get("fechaAlta"), inicio, fin);
            } else if (inicio != null) {
                return cb.greaterThanOrEqualTo(root.get("fechaAlta"), inicio);
            } else if (fin != null) {
                return cb.lessThanOrEqualTo(root.get("fechaAlta"), fin);
            } else {
                return null;
            }
        };
    }
}
