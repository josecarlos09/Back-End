package br.com.back_end.clinica_hospitalar.service.agendamento;

import br.com.back_end.clinica_hospitalar.dto.agendamento.DadosAgendamentoConsulta;
import br.com.back_end.clinica_hospitalar.infraestrutura.exception.ValidacaoExcecao;
import br.com.back_end.clinica_hospitalar.model.agendamento.AgendamentoConsulta;
import br.com.back_end.clinica_hospitalar.model.medico.Medico;
import br.com.back_end.clinica_hospitalar.repository.AgendamentoRepository;
import br.com.back_end.clinica_hospitalar.repository.MedicoRepository;
import br.com.back_end.clinica_hospitalar.repository.PacienteRepository;
import br.com.back_end.clinica_hospitalar.dto.agendamento.DadosDetalhadosConsulta;
import br.com.back_end.clinica_hospitalar.dto.agendamento.DadosCancelamentoConsulta;
import br.com.back_end.clinica_hospitalar.service.agendamento.validacoes.ValidadorAgendaConsultaMedica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class AgendamentoConsultaService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidadorAgendaConsultaMedica> validadores;

    // Ao colocar a interfece em uma lista eu posso usar qualquer regra de negocio que ela implementa

    public DadosDetalhadosConsulta agendar(DadosAgendamentoConsulta dados){
       // Se o paciente informado não estiver cadastrado será disparado a exceção
        if (!pacienteRepository.existsById(dados.idPaciente())){
            throw new ValidacaoExcecao("Paciente não cadastrado!");
        }
       // Se o id do médico for difente de null, e não existir no BD será retornado a exceção
        if (dados.idMedico() != null  && !medicoRepository.existsById(dados.idMedico())){
            throw new ValidacaoExcecao("Medico não cadastrado!");
        }

        validadores.forEach(v -> v.validar(dados)); //Chamamento de toda as validações

        // Agendamento de consultas médicas com paciente e medico
        var paciente = pacienteRepository.getReferenceById(dados.idPaciente()); // A variavel paciente está recebendo o repository que contem o JPA, e chama o método findById que recebe o DTO  contendo o id da tabela paciente
        //var medico = medicoRepository.findById(dados.idMedico()).get(); // A variavel medico está recebendo o repository que contem o JPA, e chamando o método findById que recebe o DTo contendo da tabela medico
        var medico = escolherMedico(dados);

        var agendamentoConsulta = new AgendamentoConsulta(null, medico, paciente, dados.dataAgendamento(), null);
        agendamentoRepository.save(agendamentoConsulta);

        if (medico == null){
            throw new ValidacaoExcecao("Não existe médico disponivel nessa data");
        }
        return new DadosDetalhadosConsulta(agendamentoConsulta);
    }

    private Medico escolherMedico(DadosAgendamentoConsulta dados) {
        // Segundo a regra de negocio o medico vai ser escolhido aleatoriamente seguindo os criterios de  disponibilidade e especialidade
        if (dados.idMedico() != null  && medicoRepository.existsById(dados.idMedico())){
            return medicoRepository.getReferenceById(dados.idMedico()); // será retornado o médico selecionado
        }

        if (dados.especialidade() == null){
            throw  new ValidacaoExcecao("A especialidade é obrigatoria quando o médico não for informado");
        }
        // A escolha do médico aleatoria será feito diretamente no BD atraves da Query do JPQL que está anotada no método escolherMedicoAleatorio
        return medicoRepository.escolherMedicoAleatorio(dados.especialidade(), dados.dataAgendamento());
    }

    public void cancelamentoConsulta(DadosCancelamentoConsulta dados){
        // cancelamento da consulta marcada
        if (!agendamentoRepository.existsById(dados.idConsulta())){
            throw new ValidacaoExcecao("Id da consulta informada não existe");
        }
        // Se a consulta maracda existir ela será cancelada
        var consulta = agendamentoRepository.getReferenceById(dados.idConsulta());
        consulta.cancelar(dados.motivo());
    }

    public void apagarRegistro(Long id){
        // O método exclusaoFisica apaga os dados do banco de dados
        this.agendamentoRepository.deleteById(id);
    }

    public ResponseEntity consultaDetalhada(@PathVariable Long id){
        var agendamento = agendamentoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhadosConsulta(agendamento));
    }

}