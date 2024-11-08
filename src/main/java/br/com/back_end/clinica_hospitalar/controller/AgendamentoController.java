package br.com.back_end.clinica_hospitalar.controller;

import br.com.back_end.clinica_hospitalar.dto.agendamento.DadosAgendamentoConsulta;
import br.com.back_end.clinica_hospitalar.dto.agendamento.DadosCancelamentoConsulta;
import br.com.back_end.clinica_hospitalar.dto.agendamento.DadosDetalhadosConsulta;
import br.com.back_end.clinica_hospitalar.service.agendamento.AgendamentoConsultaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("agendamentoConsulta")
public class AgendamentoController {

    @Autowired
    private AgendamentoConsultaService agendamentoConsultaService;

    @PostMapping("/agenda")
    @Transactional
    public ResponseEntity<DadosDetalhadosConsulta> agendar(@RequestBody @Valid DadosAgendamentoConsulta dados){
        System.out.println(dados);

       var dto = agendamentoConsultaService.agendar(dados);
       return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/cancelados")
    @Transactional
    public ResponseEntity cancelarAgendamento(@Valid @RequestBody DadosCancelamentoConsulta dados){
        System.out.println(dados);

        agendamentoConsultaService.cancelamentoConsulta(dados);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/consulta_detalhada/{id}")
    public ResponseEntity consultaDetalhada(@PathVariable Long id){
        return agendamentoConsultaService.consultaDetalhada(id);
    }

    @DeleteMapping("/deletar_registro/{id}")
    @Transactional
    public void deltarRegistro(@PathVariable Long id) {
        this.agendamentoConsultaService.apagarRegistro(id);
    }


}

