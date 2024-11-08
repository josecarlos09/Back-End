package br.com.back_end.clinica_hospitalar.model.agendamento;

import br.com.back_end.clinica_hospitalar.dto.agendamento.MotivoCancelamento;
import br.com.back_end.clinica_hospitalar.model.medico.Medico;
import br.com.back_end.clinica_hospitalar.model.paciente.Paciente;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "agendamento") // Nome da tabela no banco
@Table(name = "agendamento") // Como vai ser chamada no spring
@EqualsAndHashCode(of = "id")// Indicaa a chave primary-key

// Construtores
@AllArgsConstructor // Construtor que inclua todos os atributos de uma classe no Spring
@NoArgsConstructor // Serve para criar um construtor padrão

// Métodos acessores e modificadores
@Getter
@Setter
public class AgendamentoConsulta{
    @Id // Indica qual é o atributo chave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)// Gera números sequenciais
    @Column(name = "id")
    protected Long id;
    /*
     * @ManyToOne: Indica que existe um relacionamento "muitos para um" entre duas entidades
     * @ManyToOne: Indica que existe um relacionamento "muitos para um" entre duas entidades
     * fetch = FetchType.LAZY: Define a estratégia de carregamento. Com FetchType.LAZY, a entidade Medico não será carregada automaticamente junto com a entidade atual
     * @JoinColumn(name = "medico_id", referencedColumnName = "id"): Define a coluna que será usada como chave estrangeira na tabela da entidade atual.
    */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_id", referencedColumnName = "id")
    private Medico medico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id", referencedColumnName = "id")
    private Paciente paciente;

    private LocalDateTime dataConsulta;

    // O motivo do cancelamento foi criado para tratar o senario: Se o agendamento da consulta for cancelado?
    @Column(name = "motivo_cancelamento")
    @Enumerated(EnumType.STRING)
    private MotivoCancelamento motivoCancelamento;

    public AgendamentoConsulta(Medico medico, Paciente paciente, LocalDateTime data) {
        this.medico = medico;
        this.paciente = paciente;
        this.dataConsulta = data;
    }

    // O motivo será informado de dentro de um ENUM
    public void cancelar(MotivoCancelamento motivo) {
        this.motivoCancelamento = motivo;
    }

}
