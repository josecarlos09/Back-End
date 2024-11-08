package br.com.back_end.clinica_hospitalar.dto.medico;

import br.com.back_end.clinica_hospitalar.model.medico.Especialidade;

public record DadosExcluirMedico(
        Long id,
        String nome,
        String email,
        String telefone,
        String crm,
        Especialidade especialidade,
        String formacao,
        Boolean ativo){
}
