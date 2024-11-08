package br.com.back_end.clinica_hospitalar.repository;

import br.com.back_end.clinica_hospitalar.model.paciente.Paciente;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository //O carater dessa anotação é semantico o que indica que essa é uma repositry é o fato dela ser uma interface que extende o JpaRepository.
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    Page<Paciente> findAllByAtivoTrue(Pageable paginacao);

    // Estou buscando apanas o campo ativo do paciente na tabela do BD
    @Query("""
           select p.ativo
           from paciente p
           where
           p.id = :id
           """)
    Boolean findAtivoById(@NotNull @Param("id") Long idPaciente);
}

