package br.unipar.devbackend.trabalho2b.repository;

import br.unipar.devbackend.trabalho2b.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> { }
