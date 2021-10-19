package com.digitalhouse.blog.controller;

import com.digitalhouse.blog.model.Postagem;
import com.digitalhouse.blog.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Definindo a classe como um controller
@RestController
// Mapeando Postagem
@RequestMapping("/postagens")   // Ajuste da pagina de carregamento
// Captura qualquer referência a Postagem
@CrossOrigin("*")   // Para não ter problemas com as origens, seja react, angular etc.
public class PostagemController {

    @Autowired      // Como é uma interface deixamos o trabalho da injeção a cargo do proprio Spring
    private PostagemRepository repository;  // Injetando o repositório

    @GetMapping  // Sempre que vir algo externo através da URL, será tratado aqui

    public ResponseEntity<List<Postagem>> GetAll() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Postagem> GetById(@PathVariable Long id) {
        // Precisamos usar dentro da passagem do parâmetro a anotação PathVariable pra dizer
        // ao Spring que cuide da parte de trazer da url a informação e use com parametro.
        return repository.findById(id).map(resp -> ResponseEntity.ok(resp))
                .orElse(ResponseEntity.notFound().build());
        // Se der tudo certo o objeto encontrado vem no responseEntity.ok
        // pode não existir então o orElse informar o not found
    }

    // Pesquisar pelo título
    @GetMapping("titulo/{titulo}")
    // Lembrando, podemos ter 1 postagem ou mais de uma postagem com o titulo
    // por isso nesse caso lembrando o primeiro mapping nós iremos fazer uma
    // List do tipo postagem para este retorno
    public ResponseEntity<List<Postagem>> GetByTitulo(@PathVariable String titulo) {
        return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
    }

    // Pesquisar pelo texto
    @GetMapping("texto/{texto}")
    public ResponseEntity<List<Postagem>> GetByTexto(@PathVariable String texto) {
        return ResponseEntity.ok(repository.findAllByTextoContainingIgnoreCase(texto));
    }

    @PostMapping
    public ResponseEntity<Postagem> post(@RequestBody Postagem postagem) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(postagem));
    }

    @PutMapping
    public ResponseEntity<Postagem> put(@RequestBody Postagem postagem) {
        return ResponseEntity.status(HttpStatus.OK).body(repository.save(postagem));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}