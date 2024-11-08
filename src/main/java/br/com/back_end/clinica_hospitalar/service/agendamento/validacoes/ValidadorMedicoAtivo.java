package br.com.back_end.clinica_hospitalar.service.agendamento.validacoes;

import br.com.back_end.clinica_hospitalar.dto.agendamento.DadosAgendamentoConsulta;
import br.com.back_end.clinica_hospitalar.infraestrutura.exception.ValidacaoExcecao;
import br.com.back_end.clinica_hospitalar.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoAtivo implements ValidadorAgendaConsultaMedica {
    @Autowired
    private MedicoRepository medicoRepository;

    @Override
    public void validar(DadosAgendamentoConsulta dados){
        /*
         * Não permitir o agendamento de consultas com médicos inativos.
         */

        // Escolha do médico é opcional
        if (dados.idMedico() == null){
            return;
        }

        var medicoAtivo = medicoRepository.findAtivoById(dados.idMedico());
        if(!medicoAtivo) {
            throw new ValidacaoExcecao("Médico desativado da clinica");
        }
    }
}
