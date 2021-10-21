package com.digitalhouse.blog.service;

import org.apache.commons.codec.binary.Base64;
import com.digitalhouse.blog.model.UserLogin;
import com.digitalhouse.blog.model.Usuario;
import com.digitalhouse.blog.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

// Tornando está classe um Service
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public Usuario cadastrarUsuario(Usuario usuario) {
        // Trabalhando com a senha encriptada (encodada)
        // Criando um encoder para criptografar a senha
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        // Recuperando e encriptar a senha do usuário
        String senhaEncoder = encoder.encode(usuario.getSenha());
        // Gravando a senha encriptada
        usuario.setSenha(senhaEncoder);
        // Retornando o usuário com a senha encriptada
        return repository.save(usuario);
    }

    // Interface Optional: evitar NullPointException por retorno null
    public Optional<UserLogin> logar(Optional<UserLogin> user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Optional<Usuario> usuario = repository.findByUsuario(user.get().getUsuario());

        if(usuario.isPresent()) {
            // Comparar se a senha fornecida bate com a senha gravada
            if(encoder.matches(user.get().getSenha(),usuario.get().getSenha())) {

                String auth = user.get().getUsuario() + ":" + user.get().getSenha();
                byte[] encodeAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
                String authHeader = "Basic " + new String(encodeAuth);

                user.get().setToken(authHeader);
                user.get().setNome(usuario.get().getNome());

                return user;
            }
        }
        return null;
    }
}
