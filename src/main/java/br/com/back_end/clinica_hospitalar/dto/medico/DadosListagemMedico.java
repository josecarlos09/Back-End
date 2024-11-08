package br.com.back_end.clinica_hospitalar.dto.medico;

import br.com.back_end.clinica_hospitalar.model.medico.Especialidade;
import br.com.back_end.clinica_hospitalar.model.medico.Medico;

public record DadosListagemMedico(Long id, String nome, String email, String crm, Especialidade especialidade, Boolean ativo) {

    public DadosListagemMedico(Medico medico){
         this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade(), medico.getAtivo());
    }
}
