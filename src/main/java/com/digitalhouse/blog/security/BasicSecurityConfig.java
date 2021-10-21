package com.digitalhouse.blog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// Definindo a classe para implementar Spring Security
@EnableWebSecurity
// Configurando um adaptador de segurança
public class BasicSecurityConfig extends WebSecurityConfigurerAdapter {

    // Injetando a dependência de segurança Spring Security
    @Autowired
    private UserDetailsService userDetailsService;

    // Gerenciar a autorização de acesso
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    // Definindo o tipo de encriptação dos dados / senha
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Tratando as requisições HTTP
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // O verbos GET para logar e cadastrar serão permitidos
        // pois não é necessário token para ambos
        // Para as outras requisições, o usuário precisará estar autenticado
        http.authorizeRequests()
                .antMatchers("/usuario/logar").permitAll()
                .antMatchers("/usuario/cadastrar").permitAll()
                // Exigir autenticação
                .anyRequest().authenticated()
                .and().httpBasic()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().cors()
                .and().csrf().disable();
    }
}
