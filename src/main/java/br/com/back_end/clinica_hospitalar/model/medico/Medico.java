package br.com.back_end.clinica_hospitalar.model.medico;
import br.com.back_end.clinica_hospitalar.dto.medico.DadosAtualizacaoMedico;
import br.com.back_end.clinica_hospitalar.dto.medico.DadosCadastroMedico;
import br.com.back_end.clinica_hospitalar.model.endereco.Endereco;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity(name = "medico") // Nome da tabela no banco
@Table(name = "medico") // Como vai ser chamada no spring
@EqualsAndHashCode(of = "id")// Indicaa a chave primary-key

// Construtores
@AllArgsConstructor // Construtor que inclua todos os atributos de uma classe no Spring
@NoArgsConstructor // Serve para criar um construtor padrão

// Métodos acessores e modificadores
@Getter
@Setter

public class Medico {
    @Id // Indica qual é o atributo chave primaria
    @GeneratedValue(strategy = GenerationType.SEQUENCE)// Gera números sequenciais
    @Column(name = "id")
    protected Long id;

    // serve para crirar o campo na tabela do BD.
    // Pode ser um nome difernte do atribulto da classe
    @Column(name = "nome")
    @NotBlank(message = "{nome.obrigatorio}")
    private String nome;

    @Email
    @NotBlank(message = "email.obrigatorio")
    @Column(name = "email")
    private String email;

    @NotBlank(message = "{telefone.obrigatorio}")
    private String telefone;

    @NotBlank(message = "{crm.obrigatorio}")
    @Column(name = "crm")
    private String crm;

    private Especialidade especialidade;

    @Embedded // Verificar como funciona. na classe Endereco terá a anotação @Embeddable
    private Endereco endereco;

    @NotBlank(message = "{formacao.obrigatorio}, {email.invalido}")
    private String formacao;

    @Column(name = "ativo")
    private Boolean ativo;

    // Construtor que passa o DTO
    public Medico(DadosCadastroMedico dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.crm = dados.crm();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());
        this.telefone = dados.telefone();
        this.formacao = dados.formacao();
        this.ativo = dados.ativo();
    }

    public void atualizarInformacoes(@Valid DadosAtualizacaoMedico dadosAtualizacaoMedico) {

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

    public void inativar() {
        this.ativo=false;
    }
}
