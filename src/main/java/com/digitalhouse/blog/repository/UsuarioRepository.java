package com.digitalhouse.blog.repository;

import com.digitalhouse.blog.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// Definindo a interface como um Repository
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    public Optional<Usuario> findByUsuario(String usuario);

}
