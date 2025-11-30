package br.unipar.devbackend.trabalho2b.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class AulasDadasPresencas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "aula_dada_id")
    private AulasDadas aulaDada;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    private Boolean falta;
}
