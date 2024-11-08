package br.com.back_end.clinica_hospitalar.service.parciente;

import br.com.back_end.clinica_hospitalar.dto.paciente.DadosAtualizarPaciente;
import br.com.back_end.clinica_hospitalar.dto.paciente.DadosCadastroPaciente;
import br.com.back_end.clinica_hospitalar.dto.paciente.DadosDetalhadoPaciente;
import br.com.back_end.clinica_hospitalar.dto.paciente.DadosListagemPaciente;
import br.com.back_end.clinica_hospitalar.model.paciente.Paciente;
import br.com.back_end.clinica_hospitalar.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class PacienteService {
    // Injeção de dependencia dos repositorys
    @Autowired
    PacienteRepository pacienteRepository;

    public ResponseEntity<DadosDetalhadoPaciente> inserirPaciente(DadosCadastroPaciente dadosPaciente, UriComponentsBuilder uriComponentsBuilder){
        var paciente = new Paciente(dadosPaciente);
        pacienteRepository.save(paciente); // Salva as informações no BD

        // Gerando cabeçalho
        // Como é um cadatastro, o tipo do retorno será código 201
        var uri = uriComponentsBuilder.path("/pacinete/{id}").buildAndExpand(paciente.getId()).toUri();
        /*
           * Sera devolvido no corpo da requisição um DTO para detalhar os dados
           * Por quê criar outro DTO em vez de usar o DadosCadastroPaciente?
           Porque não será mostrado no corpo da requisição o mesmo DTO usado para cadastrar as informações
        */
        return ResponseEntity.created(uri).body(new DadosDetalhadoPaciente(paciente));
    }

    public ResponseEntity<Page<DadosListagemPaciente>> listar(Pageable paginacao){
        // Estudar mais como findAllByAtivoTrue funciona
        var page = pacienteRepository.findAllByAtivoTrue(paginacao).map(DadosListagemPaciente:: new);
        // Retornara no corpo da requisição a consultar
        return ResponseEntity.ok(page);
    }

    public ResponseEntity modificar(DadosAtualizarPaciente atualizarDados){
        //Vai modificar atravez do ID informado
        var paciente = pacienteRepository.getReferenceById(atualizarDados.id());
        paciente.atualizarInformacoes(atualizarDados);
        return ResponseEntity.ok(new DadosDetalhadoPaciente(paciente));
    }

    public ResponseEntity excluir(Long id){
        // Faz exclusão logica
        var paciente = pacienteRepository.getReferenceById(id);
        paciente.inativor();
        return ResponseEntity.noContent().build();
    }

    public void apagar(Long numero_id){
        // O método exclusaoFisica apaga os dados do banco de dados
        this.pacienteRepository.deleteById(numero_id);
    }

    public ResponseEntity consultaDetalhada(@PathVariable Long id){
        // Será realizado uma consultar pelo id informado
        var paciente = pacienteRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhadoPaciente(paciente));
    }
}


