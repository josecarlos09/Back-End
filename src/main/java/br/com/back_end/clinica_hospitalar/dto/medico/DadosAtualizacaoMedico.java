package br.com.back_end.clinica_hospitalar.dto.medico;

import br.com.back_end.clinica_hospitalar.dto.endereco.DadosEndereco;
import com.fasterxml.jackson.annotation.JsonAlias;

public record DadosAtualizacaoMedico(
        Long id,
        @JsonAlias("Nome") String nome,
        @JsonAlias("Telefone") String telefone,
        @JsonAlias("dados endereço") DadosEndereco endereco) {
}
