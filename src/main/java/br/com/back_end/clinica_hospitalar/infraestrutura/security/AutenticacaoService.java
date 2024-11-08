package br.com.back_end.clinica_hospitalar.infraestrutura.security;

import br.com.back_end.clinica_hospitalar.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Implementação do método UserDetails da UserDetailsService para autenticar o usuario
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return usuarioRepository.findByLogin(login);// Será realizado a consultar dos usuarios no banco de dados
    }
}
