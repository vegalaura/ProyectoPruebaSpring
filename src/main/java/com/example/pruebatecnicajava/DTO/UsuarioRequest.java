package com.example.pruebatecnicajava.DTO;

import com.example.pruebatecnicajava.entity.StatusUsuario;
import com.example.pruebatecnicajava.entity.Usuario;
import com.example.pruebatecnicajava.utils.Encrypt;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRequest {
    private String password;
        private String login;
        private String nombre;
        private Float cliente;
        private String email;
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

        public Usuario convertirAEntity() throws NoSuchAlgorithmException {
            Usuario usuario = new Usuario();
            usuario.setPassword(Encrypt.encriptarSHABase64(password));
            usuario.setLogin(login);
            usuario.setNombre(nombre);
            usuario.setCliente(cliente);
            usuario.setEmail(email);
            usuario.setFechaBaja(fechaBaja);
            usuario.setStatus(status);
            usuario.setIntentos(intentos);
            usuario.setFechaRevocado(fechaRevocado);
            usuario.setFechaVigencia(fechaVigencia);
            usuario.setNoAcceso(noAcceso);
            usuario.setApellidoPaterno(apellidoPaterno);
            usuario.setApellidoMaterno(apellidoMaterno);
            usuario.setArea(area);
            usuario.setFechaModificacion(fechaModificacion);
            usuario.setIntentos(0F);
            return usuario;
        }
}
