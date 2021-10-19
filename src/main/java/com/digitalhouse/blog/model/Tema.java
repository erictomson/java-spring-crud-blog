package com.digitalhouse.blog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

// implements Serializable:
// Necessário serializar a classe para que os dados
// sejam trocadas de forma binária (e não objetos Java)

// Tornando a classe uma entidade gerenciavel
// Esta entidade se tornará uma tabela no database
@Entity
// PO do projeto resolveu chamar e entidade Tema de tb_tema no banco de dados
@Table(name="tb_tema")
public class Tema implements Serializable {

    // Atributos da classe

    // Definindo a chave primária (PK)
    @Id
    // Gerando a chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    // Definindo campos como Not Null
    @NotNull
    @Size(min=5,max=25)
    private String descricao;
    // Cardinalidade: 1 tema para muitas postagens
    @OneToMany (mappedBy = "tema", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("tema")
    private List<Postagem> postagens;
    // Getters e Setters

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Postagem> getPostagens() {
        return postagens;
    }

    public void setPostagens(List<Postagem> postagens) {
        this.postagens = postagens;
    }
}
