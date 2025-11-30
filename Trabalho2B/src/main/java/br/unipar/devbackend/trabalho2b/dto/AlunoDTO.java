package br.unipar.devbackend.trabalho2b.dto;

import lombok.Data;

@Data
public class AlunoDTO {
    private Long id;
    private String nome;
    private String cpf;
    private String ra;
    private Integer anoIngresso;
    private Integer periodoAtual;
}
