package com.example.pruebatecnicajava.service;

import com.example.pruebatecnicajava.DTO.LoginDTO;
import com.example.pruebatecnicajava.DTO.UsuarioRequest;
import com.example.pruebatecnicajava.DTO.UsuarioResponse;
import com.example.pruebatecnicajava.entity.Usuario;
import com.example.pruebatecnicajava.repository.UsuarioRepository;
import com.example.pruebatecnicajava.repository.UsuarioSpecifications;
import com.example.pruebatecnicajava.utils.Encrypt;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.time.LocalDate;

@Service
public class LoginService {

    private final UsuarioRepository usuarioRepository;

    public LoginService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public boolean validarLogin(LoginDTO login) throws Exception {
        Usuario usuario = usuarioRepository.findByLogin(login.getLogin())
            .orElseThrow(() -> new Exception("Usuario no existe"));
        LocalDate dateNow = LocalDate.now();
        if (usuario.getFechaVigencia() != null && usuario.getFechaVigencia().isBefore(dateNow)) {
            throw new Exception("Usuario vencido. Acceso denegado.");
        }
        String passwordEncriptada = Encrypt.encriptarSHABase64(login.getPassword());
        if (!passwordEncriptada.equals(usuario.getPassword())) {
            throw new Exception("Contraseña incorrecta");
        }

        return true;
    }

    public boolean eliminarUsuario(String login)
    {
        return usuarioRepository.deleteByLogin(login) > 0;
    }

    public List<UsuarioResponse> obtenerUsuariosRegistrados()
    {
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<UsuarioResponse> usuarioResponses = usuarios.stream().map(Usuario::convertirUsuarioResponse)
            .collect(Collectors.toList());
        return usuarioResponses;
    }

    public void crearUsuario(UsuarioRequest usuarioRequest) throws Exception
    {
       if (usuarioRepository.save(usuarioRequest.convertirAEntity()) == null)
       {
           throw new RuntimeException("Error al crear el usuario");
       }
    }

    public List<UsuarioResponse> buscarUsuarios(String nombre, String estatus, LocalDate inicio, LocalDate fin) {
        return usuarioRepository.findAll(
            Specification
                .where(UsuarioSpecifications.conNombre(nombre))
                .and(UsuarioSpecifications.conEstatus(estatus))
                .and(UsuarioSpecifications.conFechaAltaEntre(inicio, fin))
        ).stream().map(Usuario::convertirUsuarioResponse).collect(Collectors.toList());
    }

}
