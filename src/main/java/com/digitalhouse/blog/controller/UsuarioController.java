package com.digitalhouse.blog.controller;

import com.digitalhouse.blog.model.UserLogin;
import com.digitalhouse.blog.model.Usuario;
import com.digitalhouse.blog.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

// Definindo a classe como um controller
@RestController
// Mapeando o End Point
@RequestMapping("/usuario")
// Captura qualquer referência: permite acesso externo
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class UsuarioController {

    // Injetando o repositório via Spring
    // Será usado como se fosse um objeto
    @Autowired
    private UsuarioService usuarioService;

    // RequestBody: JSON
    @PostMapping("/logar")
    public ResponseEntity<UserLogin> authentication(@RequestBody Optional<UserLogin> user) {
        return usuarioService.logar(user).map(resp -> ResponseEntity.ok(resp))
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Usuario> post(@RequestBody Usuario usuario) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(usuarioService.cadastrarUsuario(usuario));
    }
}
