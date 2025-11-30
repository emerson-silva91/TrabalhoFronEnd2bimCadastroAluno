package br.unipar.devbackend.trabalho2b.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class AulasDadas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Disciplina disciplina;

    private LocalDate data;

    private String observacoes;

    @OneToMany(mappedBy = "aulaDada", cascade = CascadeType.ALL)
    private List<AulasDadasPresencas> presencas;
}
