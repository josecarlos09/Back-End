package br.com.back_end.clinica_hospitalar.service.agendamento.validacoes;

import br.com.back_end.clinica_hospitalar.dto.agendamento.DadosAgendamentoConsulta;
import br.com.back_end.clinica_hospitalar.infraestrutura.exception.ValidacaoExcecao;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorHorarioMarcaConsulta implements ValidadorAgendaConsultaMedica{

    @Override
    public void validar(DadosAgendamentoConsulta dados) {
        var dataConsulta = dados.dataAgendamento();

        var agora = LocalDateTime.now(); // LocalDateTime.now() obtém a data e a hora atuais.

        var diferencaEmMinutos = Math.abs(Duration.between(agora, dataConsulta).toMinutes());
        /*
         * Duration.between(agora, dataConsulta) calcula a diferença entre os dois instantes de tempo (agora e dataConsulta)
         * O método toMinutes() converte a duração em minutos.
         * O calculo da diferença de tempo está sendo jogado dentro do Math.abs para que ele não receba um valor negativo
         */

        if (diferencaEmMinutos < 30){
            throw new ValidacaoExcecao("A consulta deve ser marcada com no minimo 30 minutos de antecedência");
        }
    }
}
