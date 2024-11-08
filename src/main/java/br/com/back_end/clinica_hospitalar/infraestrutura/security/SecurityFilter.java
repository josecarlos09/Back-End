package br.com.back_end.clinica_hospitalar.infraestrutura.security;

import br.com.back_end.clinica_hospitalar.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    TokenService tokenService;

    @Autowired
    UsuarioRepository usuarioRepository;

    // Implementação do filter interno
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var tokenJWT = this.recuperarToken(request); // Coloca dentro de uma variavel chamada tokenJWT o método recuperarToken que vem como parametro o token
        System.out.println("O Token recuperado é: "+ tokenJWT); // Exibe o Token JWT

        if (tokenJWT != null) {
            var login = tokenService.ValidacaoToken(tokenJWT);
            UserDetails usuario = usuarioRepository.findByLogin(login);

            var autenticacao = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(autenticacao);
        }
        filterChain.doFilter(request, response); // Estou chamando o proximo filtro
    }

    private String recuperarToken(HttpServletRequest request) {
        var autorizacaoHeder = request.getHeader("Authorization");

        if (autorizacaoHeder == null) {
            /*
             * Utilizamos o método replace da classe String do Java para apagar a palavra Bearer.
             * o método trim para apagar os espaços em branco da String:
             */
            return null;
        }
        return autorizacaoHeder.replace("Bearer ", "");
    }
}
