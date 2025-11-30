package br.unipar.devbackend.trabalho2b.controller;

import br.unipar.devbackend.trabalho2b.dto.Nota1BimDTO;
import br.unipar.devbackend.trabalho2b.dto.Nota2BimDTO;
import br.unipar.devbackend.trabalho2b.model.AlunoDisciplina;
import br.unipar.devbackend.trabalho2b.service.NotaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/aluno")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class NotaController {

    private final NotaService service;

    @PutMapping("/{idAluno}/disciplina/{idDisciplina}/1bim")
    public ResponseEntity<AlunoDisciplina> atualizar1Bim(@PathVariable Long idAluno,
                                                         @PathVariable Long idDisciplina,
                                                         @RequestBody Nota1BimDTO dto) {
        return ResponseEntity.ok(service.atualizarPrimeiroBimestre(idAluno, idDisciplina, dto.getNota1Bim(), dto.getFaltas1Bim()));
    }

    @PutMapping("/{idAluno}/disciplina/{idDisciplina}/2bim")
    public ResponseEntity<AlunoDisciplina> atualizar2Bim(@PathVariable Long idAluno,
                                                         @PathVariable Long idDisciplina,
                                                         @RequestBody Nota2BimDTO dto) {
        return ResponseEntity.ok(service.atualizarSegundoBimestre(idAluno, idDisciplina, dto.getNota2Bim(), dto.getFaltas2Bim()));
    }
}
