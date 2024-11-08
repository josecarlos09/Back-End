package br.com.back_end.clinica_hospitalar.service.agendamento.validacoes;

import br.com.back_end.clinica_hospitalar.dto.agendamento.DadosAgendamentoConsulta;
import br.com.back_end.clinica_hospitalar.infraestrutura.exception.ValidacaoExcecao;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidadorHorarioFuncionamento implements ValidadorAgendaConsultaMedica{

    @Override
    public void validar(DadosAgendamentoConsulta dados){
        var dataConsulta = dados.dataAgendamento();

        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY); // O SUNDAY indica que é DOMINGO.
        /*
         * getDayOfWeek() retorna o dia da semana (um valor do tipo DayOfWeek).
         * DayOfWeek.SUNDAY representa o domingo.
         * O método .equals() compara esses dois valores.
         */

        var antesDaAberturaDaClinica = dataConsulta.getHour() < 7; // Estou verificando o horari de abertura da clinica.
        var depoisDoEncerramentoDaClinica = dataConsulta.getHour() > 18; // A clinica fechas as 19:0, mas a consulta duara uma hora.

        if (domingo || antesDaAberturaDaClinica || depoisDoEncerramentoDaClinica) {
            throw new ValidacaoExcecao("Consulta fora do horario de funcionamento da clinica");
        }
    }
}
