package com.digitalhouse.blog.security;

import com.digitalhouse.blog.model.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserDetailsImpl implements UserDetails {

    // Implementando o serial
    private static final long serialVersionUID = 1L;

    private String username;
    private String password;

    public UserDetailsImpl(Usuario user) {
        this.username=user.getUsuario();
        this.password=user.getSenha();
    }

    public UserDetailsImpl() { }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Métodos de UserDetails implementados pela IDE
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}