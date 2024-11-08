package br.com.back_end.clinica_hospitalar.dto.medico;

import br.com.back_end.clinica_hospitalar.dto.endereco.DadosEndereco;
import br.com.back_end.clinica_hospitalar.model.medico.Especialidade;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroMedico(
        @NotBlank // Não pode ser vaziu e nem null (A anotação @NotBlank é só para campos Strings)
        String nome,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String telefone,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}")// Espreção regular de 4 a 6 digitos
        String crm,
        @NotNull
        Especialidade especialidade,
        String formacao,
        Boolean ativo,
        DadosEndereco endereco
){}
