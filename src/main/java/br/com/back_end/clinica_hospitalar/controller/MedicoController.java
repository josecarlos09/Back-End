package br.com.back_end.clinica_hospitalar.controller;

import br.com.back_end.clinica_hospitalar.dto.medico.DadosAtualizacaoMedico;
import br.com.back_end.clinica_hospitalar.dto.medico.DadosCadastroMedico;
import br.com.back_end.clinica_hospitalar.dto.medico.DadosDetalhamentoMedico;
import br.com.back_end.clinica_hospitalar.dto.medico.DadosListagemMedico;
import br.com.back_end.clinica_hospitalar.service.medico.MedicoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("medico")
@SecurityRequirement(name = "bearer-key") //Essa anotação, quando aplicada a um método ou classe, especifica na documentação da API que o endpoint precisa de autenticação e qual esquema de segurança deve ser utilizado.
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @PostMapping ("/cadastro")// Indica que será uma inserção de dados
    @Transactional // Indica que está acontecendo uma trasação ativa no BD
    public ResponseEntity<DadosDetalhamentoMedico> cadastrar(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriComponentsBuilder){
      return medicoService.inserirMedico(dados, uriComponentsBuilder);
    }

    @GetMapping("/consulta")
    public ResponseEntity<Page<DadosListagemMedico>>consultar(Pageable paginacao){
         return medicoService.listarMedicos(paginacao);
    }

    @PutMapping("/atualizacao")
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoMedico atualizarDados){
        return medicoService.modificar(atualizarDados);
    }

    @DeleteMapping("/invalido/{id}")
    @Transactional
    public ResponseEntity exclusaoLogica(@PathVariable Long id){
       return medicoService.excluir(id);
    }

    @DeleteMapping("/excluzao/{id}")
    @Transactional
    public void exclusaoFisica(@PathVariable Long id){
       this.medicoService.apagar(id);
    }

    @GetMapping("/consulta_detalhada/{id}")
    public ResponseEntity consultaDetalhada(@PathVariable Long id){
       return medicoService.consultaDetalhada(id);
    }
}