package br.com.back_end.clinica_hospitalar.service.agendamento.validacoes;

import br.com.back_end.clinica_hospitalar.dto.agendamento.DadosAgendamentoConsulta;

public interface ValidadorAgendaConsultaMedica {

    /*
     * O projeto desenvolvido traz regras de negocios bem claras a respeito do agendamento
     * As validações dos agendamentos de consultas medicas seram implementados pelo método validar da interface ValidadorAgendaConsultaMedica
     * A assinatura do método será igual, mas a funcionalidade dele será diferente (Conceito do polimofismo)
     */
    void validar(DadosAgendamentoConsulta agendamento);
}
