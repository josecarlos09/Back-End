package br.com.back_end.clinica_hospitalar.controller;

import br.com.back_end.clinica_hospitalar.dto.login.DadosAutenticacaoLogin;
import br.com.back_end.clinica_hospitalar.dto.login.DadosTokenJWT;
import br.com.back_end.clinica_hospitalar.dto.login.RegistroDoUsuarioDTO;
import br.com.back_end.clinica_hospitalar.infraestrutura.security.TokenService;
import br.com.back_end.clinica_hospitalar.model.usuario.Usuario;
import br.com.back_end.clinica_hospitalar.service.login.LoginService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("autorizacao")
public class LoginController {
    /*
       A classe LoginController dispara a requisição do usuario
    */
    @Autowired
    private TokenService tokenService; // Service do token criado. Não confunda com o LoginService do Spring

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacaoLogin dados){
        /*
         * O LoginService só será efetuado quando:
         *   1 - o token existir
         *   2 - o token for valido
         *   3 - o token tem um tempo de duração
         */
        try {
            var autenticacaoToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha()); // Receber o login e senha passada no DTO
            var autenticacao = authenticationManager.authenticate(autenticacaoToken);

            var tokenJWT = tokenService.gerarToken((Usuario) autenticacao.getPrincipal()); // Recebendo o TOKEN na variavel tokenJWT
            return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));// Passando o tokenJWT dentro de um DTO

        }catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/registro")
    public ResponseEntity registrar(@RequestBody @Valid RegistroDoUsuarioDTO dados){
        return loginService.registrar(dados);
    }
}
