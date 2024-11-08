package br.com.back_end.clinica_hospitalar.dto.login;
import br.com.back_end.clinica_hospitalar.model.usuario.UsuarioRole;

public record RegistroDoUsuarioDTO(String login, String senha, UsuarioRole role) {
}