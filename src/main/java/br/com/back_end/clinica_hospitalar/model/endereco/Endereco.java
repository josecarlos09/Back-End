package br.com.back_end.clinica_hospitalar.model.endereco;

import br.com.back_end.clinica_hospitalar.dto.endereco.DadosEndereco;
import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Embeddable// Estudar o que essa anotação faz

public class Endereco {
    //Atribultos
    private String logradouro;
    private String bairro;
    private String cep;
    private String numero;
    private String complemento;
    private String cidade;
    private String uf;

    public Endereco(DadosEndereco dadosEndereco) {
        //Construtor
        this.logradouro = dadosEndereco.logradouro();
        this.bairro = dadosEndereco.bairro();
        this.cep = dadosEndereco.cep();
        this.numero = dadosEndereco.numero();
        this.complemento = dadosEndereco.complemento();
        this.cidade = dadosEndereco.cidade();
        this.uf = dadosEndereco.uf();
    }

    public void atualizarEndereco(DadosEndereco endereco) {
        if (endereco.logradouro() != null){
            this.setLogradouro(endereco.logradouro());
        }
        if (endereco.bairro() != null){
            this.setBairro(endereco.bairro());
        }
        if (endereco.cep() != null){
            this.setCep(endereco.cep());
        }
        if (endereco.numero() != null){
            this.setNumero(endereco.numero());
        }
        if (endereco.complemento() != null){
            this.setComplemento(endereco.complemento());
        }
        if (endereco.cidade() != null){
            this.setCidade(endereco.cidade());

        }
        if (endereco.uf() != null){
            this.setUf(endereco.uf());
        }
    }
}
