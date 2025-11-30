package br.unipar.devbackend.trabalho2b.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String descricao;
    @ManyToOne
    @JoinColumn(name = "professor_id" )
    private Professor professor;



    @Column(nullable = false, unique = true)
    private String codigo;

    private String ementa;
}
