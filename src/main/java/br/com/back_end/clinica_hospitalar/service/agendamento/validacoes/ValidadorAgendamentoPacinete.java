package br.com.back_end.clinica_hospitalar.service.agendamento.validacoes;

import br.com.back_end.clinica_hospitalar.dto.agendamento.DadosAgendamentoConsulta;
import br.com.back_end.clinica_hospitalar.infraestrutura.exception.ValidacaoExcecao;
import br.com.back_end.clinica_hospitalar.repository.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorAgendamentoPacinete implements ValidadorAgendaConsultaMedica {
    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Override
    public void validar(DadosAgendamentoConsulta dados) {

        var primeroHorario = dados.dataAgendamento().withHour(7);
        var utimoHorario = dados.dataAgendamento().withHour(18);
        var pacientePossuiOutraConsultaNoDia = agendamentoRepository.existsByPacienteIdAndDataConsultaBetween(dados.idPaciente(), primeroHorario, utimoHorario);

        if (pacientePossuiOutraConsultaNoDia) {
            throw new ValidacaoExcecao("Já existe uma agendamento com esse data para esse paciente");
        }
    }
}
