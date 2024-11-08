package br.com.back_end.clinica_hospitalar.infraestrutura.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity // Indeica que eu vou fazer as configurações de segurança
public class SecurityConfigurations {

    @Autowired
    SecurityFilter securityFilter;

    // Corrente de filtro de segurança (Estou configurando novamente os filtros de segurança)
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // A politica de seção será STATELESS

                .authorizeHttpRequests(authorize -> authorize

                        .requestMatchers(HttpMethod.POST, "/autorizacao/login").permitAll() // Permite disparar a permição de login
                        //.requestMatchers(HttpMethod.POST, "/autorizacao/registrar").hasRole("ADMIN") // Só quem for administrador no sistema poderar criar um novo registro
                        .requestMatchers(HttpMethod.POST, "/autorizacao/registrar").permitAll()

                        // Documentação
                        .requestMatchers("/swagger-ui.html", "/v3/api-docs", "/swagger-ui/**").permitAll()

                        // Permições de usuarios para os MÉDICOS
                        .requestMatchers(HttpMethod.GET, "/medico/consultar").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/medico/cadastrar").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/medico/atualizar").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/medico/consulta_detalhada/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/medico/excluir/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/medico/apagar/{id}").hasRole("ADMIN")

                        // Permições de usuarios para os pacientes
                        .requestMatchers(HttpMethod.GET, "/paciente/concultar").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/paciente/cadastrar").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/paciente/atualizar").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/paciente/consulta_detalhada/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/paciente/excluir/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/paciente/apagar/{id}").hasRole("ADMIN")

                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)// faz um filtro de verificação antes do UsernamePasswordAuthenticationFilter.class
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    // Retorna uma senha criptografada
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Classe para fazer a criptografia (algoritmo de RACH)
    }
}
