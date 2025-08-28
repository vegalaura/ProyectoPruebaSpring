package com.example.pruebatecnicajava.controller;

import com.example.pruebatecnicajava.DTO.FiltrosRequest;
import com.example.pruebatecnicajava.DTO.LoginDTO;
import com.example.pruebatecnicajava.DTO.UsuarioRequest;
import com.example.pruebatecnicajava.DTO.UsuarioResponse;
import com.example.pruebatecnicajava.service.LoginService;
import java.util.List;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestBody LoginDTO login) {
        try {
            loginService.validarLogin(login);
            return ResponseEntity.ok( "Login exitoso. Usuario válido.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping(value = "/deleteLogin")
    public ResponseEntity eliminarLogin(@RequestParam String login) {
        try{
            Boolean isUserDeleted = loginService.eliminarUsuario(login);
            if (!isUserDeleted) {
                return ResponseEntity.badRequest().body("Error: No se pudo eliminar el usuario.");
            }
            return ResponseEntity.ok( "Usuario eliminado.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @GetMapping(value = "/getAllUsers")
    public ResponseEntity<List<UsuarioResponse>> obtenerUsuariosRegistrados() {
        try{
            List<UsuarioResponse> usuarioResponses = loginService.obtenerUsuariosRegistrados();
            return ResponseEntity.ok( usuarioResponses);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping(value = "/createUser")
    public ResponseEntity createUser(@RequestBody UsuarioRequest usuarioRequest)
    {
        try {
            loginService.crearUsuario(usuarioRequest);
            return ResponseEntity.ok("Usuario creado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @PostMapping(value = "/getUsuariosByFilter")
    public ResponseEntity obtenerUsuariosByFilter(@RequestBody FiltrosRequest filtros)
    {
        try {
            List<UsuarioResponse> usuarios = loginService.buscarUsuarios(filtros.getNombre(), filtros.getEstatus(), filtros.getFechaAltaInicio(), filtros.getFechaAltaFin());
            return ResponseEntity.ok(usuarios);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}
