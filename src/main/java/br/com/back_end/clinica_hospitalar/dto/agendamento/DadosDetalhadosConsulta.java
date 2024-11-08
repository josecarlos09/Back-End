package br.com.back_end.clinica_hospitalar.dto.agendamento;
import br.com.back_end.clinica_hospitalar.model.agendamento.AgendamentoConsulta;
import java.time.LocalDateTime;

public record DadosDetalhadosConsulta (Long id, Long idMedico, Long idPaciente, LocalDateTime data){

    public DadosDetalhadosConsulta(AgendamentoConsulta agendamentoConsulta) {
        this(agendamentoConsulta.getId(), agendamentoConsulta.getMedico().getId(), agendamentoConsulta.getPaciente().getId(), agendamentoConsulta.getDataConsulta());
    }
}
