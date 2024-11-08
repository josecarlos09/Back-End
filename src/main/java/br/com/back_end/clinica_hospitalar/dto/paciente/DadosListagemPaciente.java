package br.com.back_end.clinica_hospitalar.dto.paciente;
import br.com.back_end.clinica_hospitalar.model.paciente.Paciente;

public record DadosListagemPaciente(Long id, String nome, String email, String cpf, String telefone) {

    public DadosListagemPaciente(Paciente paciente){
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf(), paciente.getTelefone());
    }
}
