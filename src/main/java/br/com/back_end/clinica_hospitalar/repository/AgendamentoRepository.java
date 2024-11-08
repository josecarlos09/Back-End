package br.com.back_end.clinica_hospitalar.repository;

import br.com.back_end.clinica_hospitalar.model.agendamento.AgendamentoConsulta;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface AgendamentoRepository extends JpaRepository<AgendamentoConsulta, Long>{

    boolean existsByMedicoIdAndDataConsulta(Long idMedico, LocalDateTime data);
    boolean existsByPacienteIdAndDataConsultaBetween(@NotNull Long idPacient, LocalDateTime primeroHorario, LocalDateTime utimoHorario);
}
