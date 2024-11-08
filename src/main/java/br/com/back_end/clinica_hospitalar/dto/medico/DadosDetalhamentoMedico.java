package br.com.back_end.clinica_hospitalar.dto.medico;

import br.com.back_end.clinica_hospitalar.model.endereco.Endereco;
import br.com.back_end.clinica_hospitalar.model.medico.Especialidade;
import br.com.back_end.clinica_hospitalar.model.medico.Medico;

public record DadosDetalhamentoMedico(Long id, String nome, String email, String formacao, String crm, Especialidade especialidade, boolean ativo, Endereco endereco){

    public DadosDetalhamentoMedico(Medico medico){
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getFormacao(), medico.getCrm(), medico.getEspecialidade(), medico.getAtivo(), medico.getEndereco());
    }
}
