package br.unipar.devbackend.trabalho2b.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class AlunoDisciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Aluno aluno;

    @ManyToOne
    private Disciplina disciplina;

    private Double nota1Bim;
    private Double nota2Bim;

    private Integer faltas1Bim;
    private Integer faltas2Bim;

    private Boolean matriculado = true;

    @Enumerated(EnumType.STRING)
    private SituacaoAlunoDisciplina situacao = SituacaoAlunoDisciplina.EM_CURSO;
}
