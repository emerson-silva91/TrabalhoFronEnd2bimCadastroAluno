package br.unipar.devbackend.trabalho2b.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AulasDadasDTO {


    private LocalDate data;
    private String observacoes;
}
