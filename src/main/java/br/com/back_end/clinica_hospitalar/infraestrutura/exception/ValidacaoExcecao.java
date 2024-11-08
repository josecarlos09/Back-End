package br.com.back_end.clinica_hospitalar.infraestrutura.exception;

public class ValidacaoExcecao extends RuntimeException {
    public ValidacaoExcecao(String mensagem) {
        super(mensagem);
    }
}
