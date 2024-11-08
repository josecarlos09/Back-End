package br.com.back_end.clinica_hospitalar.service.medico;
import br.com.back_end.clinica_hospitalar.dto.medico.DadosAtualizacaoMedico;
import br.com.back_end.clinica_hospitalar.dto.medico.DadosCadastroMedico;
import br.com.back_end.clinica_hospitalar.dto.medico.DadosDetalhamentoMedico;
import br.com.back_end.clinica_hospitalar.dto.medico.DadosListagemMedico;
import br.com.back_end.clinica_hospitalar.model.medico.Medico;
import br.com.back_end.clinica_hospitalar.repository.MedicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class MedicoService {

    @Autowired
    MedicoRepository medicoRepository;

    public ResponseEntity<DadosDetalhamentoMedico> inserirMedico(DadosCadastroMedico dadosCadastroMedico, UriComponentsBuilder uriComponentsBuilder){
        // Como é um cadatastro, o tipo do retorno será código 201
        var dadosMedico = new Medico(dadosCadastroMedico);  // Recebe o DTO dentro da entidade
        medicoRepository.save(dadosMedico); // Salva as informações do DTO em um BD
        // Gera um cabeçalho
        var uri = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(dadosMedico.getId()).toUri();
        // Retorna a requisição para o corpo
        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(dadosMedico));
    }

    public ResponseEntity<Page<DadosListagemMedico>>listarMedicos(Pageable paginacao){
        // Recebe um paginação e transforma em um map
        var page = medicoRepository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico:: new);
        return ResponseEntity.ok(page); // Retornara no corpo da requisição
    }

    public ResponseEntity modificar(@RequestBody @Valid DadosAtualizacaoMedico atualizarDados){
        /* As informações que serão alteradas é especificada pelo DTO DadosAtualizacaoMedico
         * Para indentificar o registro que será atualizado será usado a chave primaria da tabela, que é o id.
         */
        var medico = medicoRepository.getReferenceById(atualizarDados.id());
        medico.atualizarInformacoes(atualizarDados);

        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

    public ResponseEntity excluir(@PathVariable Long numero_id){
        // o Excluir fara uma esclusão logica
        /*
         1 - Foi criado uma migration para adicionar o campo ativo (indica se o médico está ativo ou não)
         2 - Foi criado uma função inativar na entidade médico, que torna o ativo em status falso.
         */
        var medico = medicoRepository.getReferenceById(numero_id);//Fara referência a tabela que possui o id informado
        medico.inativar();// O método inativar está na entidade medico, transformando o ativo igual a false.
        return ResponseEntity.noContent().build(); // Indica requisição processada e corpo vazio
    }

    public void apagar(Long numero_id){
        // O método apagar faz uma exclusão fisica os dados do banco de dados
        this.medicoRepository.deleteById(numero_id);
    }

    public ResponseEntity consultaDetalhada(@PathVariable Long id){
        /* O método consultaDetalhada recebe um DTO que será passado para o usuario que fizer a requisição
         * Por que não usar o mesmo DTO do cadastro? Não utilizei o mesmo DTO do cadastro, porque terá informações cadastradas que não será informado ao usuario.
         */
        var medico = medicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }
}