package br.unipar.devbackend.trabalho2b.dto;

import lombok.Data;

@Data
public class DisciplinaDTO {

    private Long id;
    private String codigo;
    private String descricao;
    private String ementa;
    private Long professorId;
}
