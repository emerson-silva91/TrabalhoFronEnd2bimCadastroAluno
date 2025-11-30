package br.unipar.devbackend.trabalho2b.repository;

import br.unipar.devbackend.trabalho2b.model.AulasDadas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AulasDadasRepository extends JpaRepository<AulasDadas, Long> { }
