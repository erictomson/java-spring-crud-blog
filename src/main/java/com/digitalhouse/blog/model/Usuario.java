package com.digitalhouse.blog.model;

// implements Serializable:
// Necessário serializar a classe para que os dados
// sejam trocadas de forma binária (e não objetos Java)

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

// Tornando a classe uma entidade gerenciavel
// Esta entidade se tornará uma tabela no database
@Entity
// Definindo o nome da entidade como tb_tema no banco de dados
@Table(name="tb_usuario")
public class Usuario {

    // Atributos da classe

    // Definindo a chave primária (PK)
    @Id
    // Gerando a chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // Definindo campos como Not Null
    @NotNull
    // Tamanho do campo
    @Size(min=2,max=100)
    private String nome;
    @NotNull
    @Size(min=5,max=100)
    private String usuario;
    @NotNull
    // Tamanho leva em consideração senha encriptada
    @Size(min=5,max=100)
    private String senha;

    // Getters e Setter da classe

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
