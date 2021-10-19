package com.digitalhouse.blog.controller;

import com.digitalhouse.blog.model.Tema;
import com.digitalhouse.blog.repository.TemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Definindo a classe como um controller
@Controller
// Mapeando o End Point temas
@RequestMapping("/temas")
// Captura qualquer referência a Tema
@CrossOrigin("*")
public class TemaController {

    // Injetando o repositório via Spring
    @Autowired
    private TemaRepository repository;

    // Consultar por ID
    // Mapeando End Point ID
    @GetMapping("/{id}")
    // Criando método para responder o verbo Get
    // passando o ID informado como parâmetro de entrada
    public ResponseEntity<Tema> getById(@PathVariable Long id) {
        // Chamando método findBy do repository para efetuar a pesquisa
        // retornando o resultado via mapeamento da resposta OK ou NotFound
        return repository.findById(id)
                .map(resp -> ResponseEntity.ok(resp))
                .orElse(ResponseEntity.notFound().build());
    }

    // Consultar por Descrição
    // Mapeando End Point Descrição
    @GetMapping("/temas/{descricao}")
    // Criando método para responder o verbo Get
    // passando a string descrição informada como parâmetro de entrada
    public ResponseEntity<List<Tema>> getByDescricao(String descricao) {
        // Chamando método findBy do repository para efetuar a pesquisa
        // retornando o resultado via mapeamento da resposta OK ou NotFound
        return ResponseEntity.ok(repository.findAllByDescricaoContainingIgnoreCase(descricao));
    }

    // Criando novo Tema
    // Mapeando End Point Descrição
    @PostMapping
    public ResponseEntity<Tema> post(@RequestBody Tema tema) {
        // Chamando o repository para salvar o novo Tema
        // Informando o status do post como criado
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(tema));
    }

    // Alterando um Tema
    // Mapeando End Point Descrição
    @PutMapping
    public ResponseEntity<Tema> put(@RequestBody Tema tema) {
        // Chamando o repository para salvar o Tema editado
        // Informando o status da atualização do post como OK
        return ResponseEntity.status(HttpStatus.OK).body(repository.save(tema));
    }

    // Excluindo um Tema
    // Mapeando End Point ID
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        // Chamando método delete do repository para excluir o ID
        // passado via mapeamento
        repository.deleteById(id);
    }
}
