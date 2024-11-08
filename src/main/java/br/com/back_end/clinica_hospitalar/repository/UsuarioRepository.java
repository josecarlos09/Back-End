package br.com.back_end.clinica_hospitalar.repository;

import br.com.back_end.clinica_hospitalar.model.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Méto a ser usado em AutenticacaoLoginService
    UserDetails findByLogin(String login);
}

