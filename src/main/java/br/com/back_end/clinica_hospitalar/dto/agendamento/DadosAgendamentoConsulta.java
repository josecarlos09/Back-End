package br.com.back_end.clinica_hospitalar.dto.agendamento;

import br.com.back_end.clinica_hospitalar.model.medico.Especialidade;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record DadosAgendamentoConsulta(
        Long idMedico,
        @NotNull
        Long idPaciente,
        @NotNull
        @Future
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime dataAgendamento,
        // Não pode ser NOtNull, pois só será obrigatoria quando vim o id do medico
        Especialidade especialidade,
        Boolean status){
}
