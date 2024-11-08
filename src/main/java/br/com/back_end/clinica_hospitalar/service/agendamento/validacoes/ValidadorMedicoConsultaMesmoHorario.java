package br.com.back_end.clinica_hospitalar.service.agendamento.validacoes;

import br.com.back_end.clinica_hospitalar.dto.agendamento.DadosAgendamentoConsulta;
import br.com.back_end.clinica_hospitalar.infraestrutura.exception.ValidacaoExcecao;
import br.com.back_end.clinica_hospitalar.repository.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoConsultaMesmoHorario implements ValidadorAgendaConsultaMedica{
    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Override
    public void validar(DadosAgendamentoConsulta dados){
        var medicoPossuiConsultaMesmoHorario = agendamentoRepository.existsByMedicoIdAndDataConsulta(dados.idMedico(), dados.dataAgendamento());

        if (medicoPossuiConsultaMesmoHorario){
            throw new ValidacaoExcecao("O médico já possui uma consulata agendada nesse mesmo horario");
        }
    }
}
