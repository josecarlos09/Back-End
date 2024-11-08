package br.com.back_end.clinica_hospitalar.model.paciente;

import br.com.back_end.clinica_hospitalar.dto.paciente.DadosAtualizarPaciente;
import br.com.back_end.clinica_hospitalar.dto.paciente.DadosCadastroPaciente;
import br.com.back_end.clinica_hospitalar.model.endereco.Endereco;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

@Entity(name = "paciente") // Nome da tabela no banco
@Table(name = "paciente") // Como vai ser chamada no spring
@EqualsAndHashCode(of = "id")// Indicaa a chave primary-key

// Construtores
@AllArgsConstructor // Construtor que inclua todos os atributos de uma classe no Spring
@NoArgsConstructor // Serve para criar um construtor padrão

// Métodos acessores e modificadores
@Getter
@Setter

public class Paciente {
        @Id // Indica qual é o atributo chave primaria
        @GeneratedValue(strategy = GenerationType.AUTO)// Gera números sequenciais
        @Column(name = "id")
        private Long id;
        @Column(name = "nome")
        private String nome;
        @Column(name = "email")
        private String email;
        @Column(name = "cpf")
        private String cpf;
        @Column(name = "telefone")
        private String telefone;
        @Column(name = "ativo")
        private Boolean ativo;
        @Embedded
        @Column(name = "endereco")
        private Endereco endereco;

        public Paciente(DadosCadastroPaciente dados) {
            this.nome = dados.nome();
            this.email = dados.email();
            this.telefone = dados.telefone();
            this.cpf = dados.cpf();
            this.endereco = new Endereco(dados.endereco());
            this.ativo = dados.ativo();
        }

    public void atualizarInformacoes(@Valid DadosAtualizarPaciente dadosAtualizacaoMedico) {

        if (dadosAtualizacaoMedico.nome() != null){
            this.setNome(dadosAtualizacaoMedico.nome());
        }
        if (dadosAtualizacaoMedico.telefone() != null){
            this.setTelefone(dadosAtualizacaoMedico.telefone());
        }
        if (dadosAtualizacaoMedico.endereco() != null){
            this.endereco.atualizarEndereco(dadosAtualizacaoMedico.endereco());
        }
    }

    public void inativor() {
            this.ativo = false;
    }

    public void inativar() {
        this.ativo=false;
    }
}
