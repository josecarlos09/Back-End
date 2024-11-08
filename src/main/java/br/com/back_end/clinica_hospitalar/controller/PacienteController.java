package br.com.back_end.clinica_hospitalar.controller;

import br.com.back_end.clinica_hospitalar.dto.paciente.DadosAtualizarPaciente;
import br.com.back_end.clinica_hospitalar.dto.paciente.DadosCadastroPaciente;
import br.com.back_end.clinica_hospitalar.dto.paciente.DadosDetalhadoPaciente;
import br.com.back_end.clinica_hospitalar.dto.paciente.DadosListagemPaciente;
import br.com.back_end.clinica_hospitalar.repository.PacienteRepository;
import br.com.back_end.clinica_hospitalar.service.parciente.PacienteService;
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
@RequestMapping("paciente")
@SecurityRequirement(name = "bearer-key") //Essa anotação, quando aplicada a um método ou classe, especifica na documentação da API que o endpoint precisa de autenticação e qual esquema de segurança deve ser utilizado.
public class PacienteController {
   @Autowired
   PacienteRepository pacienteRepository;

   @Autowired
   PacienteService pacienteService;

    @PostMapping("/cadastro")
    @Transactional // Indica que está acontecendo uma trasação ativa no BD
    public ResponseEntity<DadosDetalhadoPaciente> cadastrar(@RequestBody @Valid DadosCadastroPaciente dadosPaciente, UriComponentsBuilder uriComponentsBuilder){
        // Retorna o método inserirPaciente implementado no service
        return pacienteService.inserirPaciente(dadosPaciente, uriComponentsBuilder);
    }

    @GetMapping("/consulta")
    public ResponseEntity<Page<DadosListagemPaciente>> consultar(Pageable paginacao){
        // Retorna o método consultar implementado no service
        return pacienteService.listar(paginacao);
    }

    @PutMapping("/atualiazcao")
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizarPaciente atualizarDados){
        // Retorna o método modificar implementado no service
        return pacienteService.modificar(atualizarDados);
    }

    @DeleteMapping("/inativo/{id}")
    @Transactional
    public ResponseEntity exclusaoLogica(@PathVariable  Long id){
        // Retorna o método excluir implementado no service
        return pacienteService.excluir(id);
    }

    @DeleteMapping("/excluzao/{id}")
    @Transactional
    public void exclusaoFisica(@PathVariable Long id){
        this.pacienteService.apagar(id);
    }

    @GetMapping("/consulta_detalhada/{id}")
    public ResponseEntity consultaDetalhada(@PathVariable  Long id){
        // Retorna o método consultaDetalhada implementado no service
        return pacienteService.consultaDetalhada(id);
    }
}