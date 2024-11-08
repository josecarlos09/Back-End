package br.com.back_end.clinica_hospitalar.dto.agendamento;

import jakarta.validation.constraints.NotNull;

public record DadosCancelamentoConsulta(
        @NotNull
        Long idConsulta,
        @NotNull
        MotivoCancelamento motivo) {
}
