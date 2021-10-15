package com.digitalhouse.blog.repository;

import com.digitalhouse.blog.model.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PostagemRepository extends JpaRepository<Postagem,Long> {

    // Consulta pelo título
    public List<Postagem> findAllByTituloContainingIgnoreCase(String titulo);

    public List<Postagem> findAllByTextoContainingIgnoreCase(String texto);
}
