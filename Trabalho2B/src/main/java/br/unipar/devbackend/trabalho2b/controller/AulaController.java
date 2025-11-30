package br.unipar.devbackend.trabalho2b.controller;

import br.unipar.devbackend.trabalho2b.dto.AulasDadasDTO;
import br.unipar.devbackend.trabalho2b.dto.PresencaDTO;
import br.unipar.devbackend.trabalho2b.model.AulasDadas;
import br.unipar.devbackend.trabalho2b.service.AulaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/aula")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AulaController {

    private final AulaService service;

    @PostMapping("/disciplina/{idDisciplina}")
    public ResponseEntity<?> criarAula(
            @PathVariable Long idDisciplina,
            @RequestBody AulasDadasDTO aulaDTO) {
        try {
            return ResponseEntity.ok(service.criarAula(idDisciplina, aulaDTO));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @PostMapping("/{idAula}/presencas")
    public ResponseEntity<?> registrarPresencas(
            @PathVariable Long idAula,
            @RequestBody List<PresencaDTO> presencasDTO) {
        try {
            service.registrarPresencas(idAula, presencasDTO);
            return ResponseEntity.ok("Presenças registradas com sucesso");
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @GetMapping("/faltas/{idAluno}/disciplina/{idDisciplina}")
    public ResponseEntity<Long> contarFaltas(
            @PathVariable Long idAluno,
            @PathVariable Long idDisciplina) {
        long faltas = service.contarFaltasAlunoNaDisciplina(idAluno, idDisciplina);
        return ResponseEntity.ok(faltas);
    }
}
