package br.com.back_end.clinica_hospitalar.dto.paciente;

import br.com.back_end.clinica_hospitalar.dto.endereco.DadosEndereco;
import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record  DadosCadastroPaciente(
        @NotBlank(message = "{nome.obrigatorio}")
        /*  anotação @JsonAlias serve para mapear “apelidos” alternativos para os campos que serão recebidos do JSON, sendo possível atribuir múltiplos alias:*/
        @JsonAlias({"nome_paciente", "dados_nome", "nome_dados", "nome_completo"})
        String nome,

        @NotBlank(message = "email.obrigatorio")
        @Email
        @JsonAlias({"email_paciente", "E-mail", "Email" })
        String email,

        @NotBlank
        @JsonAlias({"contato", "Telefone"})
        String telefone,

        @NotBlank
        @Pattern(regexp = "\\d{8}")// Espreção regular de 4 a 6 digitos
        @JsonAlias("CPF")
        String cpf,
        Boolean ativo,

        DadosEndereco endereco){

}
