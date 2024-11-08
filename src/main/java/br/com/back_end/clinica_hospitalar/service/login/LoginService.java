package br.com.back_end.clinica_hospitalar.service.login;

import br.com.back_end.clinica_hospitalar.dto.login.RegistroDoUsuarioDTO;
import br.com.back_end.clinica_hospitalar.model.usuario.Usuario;
import br.com.back_end.clinica_hospitalar.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public ResponseEntity registrar(RegistroDoUsuarioDTO dados){
        /*
        O método registrar, registrara acessos, o registro será realizado no modelo de acesso login e Senha.
         */
        if (this.usuarioRepository.findByLogin(dados.login()) != null){
            return ResponseEntity.badRequest().build();
        }
        String senha = new BCryptPasswordEncoder().encode(dados.senha());
        Usuario novoUsuario = new Usuario(dados.login(), senha, dados.role());

        this.usuarioRepository.save(novoUsuario);
        return ResponseEntity.ok().build();
    }
}
