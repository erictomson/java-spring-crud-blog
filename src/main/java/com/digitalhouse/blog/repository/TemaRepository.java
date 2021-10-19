package com.digitalhouse.blog.repository;

import com.digitalhouse.blog.model.Tema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TemaRepository extends JpaRepository<Tema,Long> {

    // Consulta pela Descrição
    public List<Tema> findAllByDescricaoContainingIgnoreCase(String descricao);
}
