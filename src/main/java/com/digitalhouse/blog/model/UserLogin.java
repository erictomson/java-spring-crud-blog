package com.digitalhouse.blog.model;

import javax.persistence.Entity;
import java.io.Serializable;

// Tornando a classe uma entidade gerenciavel
// Esta entidade se tornará uma tabela no database
public class UserLogin {

    // Atributos da classe
    private String nome;
    private String usuario;
    private String senha;
    private String token;

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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
