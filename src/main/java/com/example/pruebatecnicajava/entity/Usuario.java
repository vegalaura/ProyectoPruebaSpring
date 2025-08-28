package com.example.pruebatecnicajava.entity;
import com.example.pruebatecnicajava.DTO.UsuarioResponse;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "usuario")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Usuario {
        @Id
        @Column(name = "login", length = 20)
        private String login;

        @Column(name = "password", nullable = false, length = 100)
        private String password;

        @Column(name = "nombre", nullable = false, length = 50)
        private String nombre;

        @Column(name = "cliente", nullable = false)
        private Float cliente;

        @Column(name = "email", length = 50)
        private String email;

        @Column(name = "fechaalta", nullable = false, updatable = false, insertable = false)
        private LocalDate fechaAlta;

        @Column(name = "fechabaja")
        private LocalDate fechaBaja;

        @Enumerated(EnumType.STRING)
        @Column(name = "status", nullable = false, length = 1)
        private StatusUsuario status;

        @Column(name = "intentos", nullable = false)
        private Float intentos;

        @Column(name = "fecharevocado")
        private LocalDate fechaRevocado;

        @Column(name = "fecha_vigencia")
        private LocalDate fechaVigencia;

        @Column(name = "no_acceso")
        private Integer noAcceso;

        @Column(name = "apellido_paterno", length = 50)
        private String apellidoPaterno;

        @Column(name = "apellido_materno", length = 50)
        private String apellidoMaterno;

        @Column(name = "area")
        private Integer area;

        @Column(name = "fechamodificacion")
        private LocalDate fechaModificacion;

        public UsuarioResponse convertirUsuarioResponse() {
            return new UsuarioResponse(this.login, this.nombre, this.cliente, this.email, this.fechaAlta, this.fechaBaja, this.status, this.intentos, this.fechaRevocado, this.fechaVigencia, this.noAcceso, this.apellidoPaterno, this.apellidoMaterno, this.area, this.fechaModificacion);
        }
}
