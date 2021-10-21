package com.digitalhouse.blog.security;

import com.digitalhouse.blog.model.Usuario;
import com.digitalhouse.blog.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

// Configurar a classe como um Service
@Service
public class UserServiceDetailsImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<Usuario> user = userRepository.findByUsuario(username);
        user.orElseThrow(() -> new UsernameNotFoundException(username + " not found."));
        return user.map(UserDetailsImpl::new).get();
    }
}
