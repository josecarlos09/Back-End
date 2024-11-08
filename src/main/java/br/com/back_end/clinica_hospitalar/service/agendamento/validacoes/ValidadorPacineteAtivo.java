package br.com.back_end.clinica_hospitalar.service.agendamento.validacoes;

import br.com.back_end.clinica_hospitalar.dto.agendamento.DadosAgendamentoConsulta;
import br.com.back_end.clinica_hospitalar.infraestrutura.exception.ValidacaoExcecao;
import br.com.back_end.clinica_hospitalar.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacineteAtivo implements ValidadorAgendaConsultaMedica{
    @Autowired
    private PacienteRepository pacienteRepository;

    @Override // Indica que estou implementando a interface ValidadorAgendaConsultaMedica
    public void validar(DadosAgendamentoConsulta dados){
        var pacienteAtivo = pacienteRepository.findAtivoById(dados.idPaciente());
        if(!pacienteAtivo){
            throw new ValidacaoExcecao("Consulta não pode acontecer com paciente inativo");
        }
    }
}
