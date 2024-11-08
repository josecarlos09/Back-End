package br.com.back_end.clinica_hospitalar.model.usuario;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity(name = "Usuario") // Nome da tabela no banco
@Table(name = "usuario") // Como vai ser chamada no spring
@EqualsAndHashCode(of = "id")// Indicaa a chave primary-key

// Construtores
@AllArgsConstructor // Construtor que inclua todos os atributos de uma classe no Spring
@NoArgsConstructor // Serve para criar um construtor padrão

// Métodos acessores e modificadores
@Getter
@Setter
/*
* A class Usuario modela a entidade que será criada no banco
* A implemetação UserDetails implementa os métodos do security
*/
public class Usuario implements UserDetails {
    @Id // Indica qual é o atributo chave primaria
    @GeneratedValue(strategy = GenerationType.AUTO)// Gera números sequenciais
    @Column(name = "id")
    private Long id;
    @Column(name = "login")
    private String login;
    @Column(name = "senha")
    private String senha;
    @Enumerated(EnumType.STRING)
    private UsuarioRole role;

    // Métodos da implementação UserDetails do spring Security
    // Controle de perfiu

    /* O construtor deve ser criado na ordem que a entidade foi feita
     */
    public Usuario(String login, String senha, UsuarioRole role) {
        this.login = login;
        this.senha = senha;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        /* O teste de usuario está divido em ADMIN e USER.
        * Se o usuario for ADMIN ele terá acesso a todas as funcionalidades referente a categoria dele(Medico ou parciente)
        * Se o usuario for USER ele terá restrições no uso da aplicação
        * */
        if (this.role == UsuarioRole.ADMIN) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));

        }else {
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return login;
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
