package br.com.back_end.clinica_hospitalar.dto.paciente;

import br.com.back_end.clinica_hospitalar.dto.endereco.DadosEndereco;

public record DadosAtualizarPaciente(
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {
}
