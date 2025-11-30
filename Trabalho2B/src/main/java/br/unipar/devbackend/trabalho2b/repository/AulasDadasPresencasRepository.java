package br.unipar.devbackend.trabalho2b.repository;

import br.unipar.devbackend.trabalho2b.model.AulasDadasPresencas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AulasDadasPresencasRepository extends JpaRepository<AulasDadasPresencas, Long> {
    long countByAlunoIdAndAulaDadaDisciplinaIdAndFaltaTrue(Long alunoId, Long disciplinaId);
}
