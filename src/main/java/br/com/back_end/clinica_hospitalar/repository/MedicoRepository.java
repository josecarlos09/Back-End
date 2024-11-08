package br.com.back_end.clinica_hospitalar.repository;

import br.com.back_end.clinica_hospitalar.model.medico.Especialidade;
import br.com.back_end.clinica_hospitalar.model.medico.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository //O carater dessa anotação é semantico o que indica que essa é uma repositry é o fato dela ser uma interface que extende o JpaRepository.
public interface MedicoRepository extends JpaRepository<Medico, Long>{
    Page<Medico> findAllByAtivoTrue(Pageable paginacao);

    /*
     * A query está buscando um médico ativo, que possui uma especialidade e que esteja disponivel na data especificada.
     * Essa operação pode ser feita carregando os registros do BD e colocando em uma lista no java, mas o uso das query otimisão o uso de memoria
     * Como é uma operação que pegarar informações do BD o uso da query é o mais ideal
     * A query vai ser escrita em JPQL
     */
    @Query("""
           select m from medico m
           where m.ativo = true
           and
           m.especialidade = :especialidade
           and
           m.id not in (
                select c.medico.id from agendamento c
                where
                c.dataConsulta = :data
           )
           order by rand()
           limit 1
           """)
    Medico escolherMedicoAleatorio(@Param("especialidade") Especialidade especialidade, @Param("data") LocalDateTime dataConsulta);

    /*
     *  m é apelido
     * :especialidade faz referencia ao parametro do método (escolherMedicoAletorio)
     * m.id not in indica que será buscado um médico que o id não esteja em consulta nessa data
    */

    @Query("""
           select m.ativo
           from medico m
           where
           m.id = :id
           """)
    Boolean findAtivoById(@Param("id") Long idMedico);
}


