package br.unipar.devbackend.trabalho2b.controller;


import br.unipar.devbackend.trabalho2b.dto.AlunoDisciplinaDTO;
import br.unipar.devbackend.trabalho2b.model.AlunoDisciplina;
import br.unipar.devbackend.trabalho2b.repository.AlunoDisciplinaRepository;
import br.unipar.devbackend.trabalho2b.service.AlunoDisciplinaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matricula")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AlunoDisciplinaController {
    private final AlunoDisciplinaService service;
    private final AlunoDisciplinaRepository repository;

    @PostMapping
    public ResponseEntity<AlunoDisciplina> matricular(@RequestBody AlunoDisciplinaDTO dto) {
        return ResponseEntity.ok(service.matricular(dto.getAlunoId(), dto.getDisciplinaId()));
    }

    @GetMapping("/disciplina/{idDisciplina}")
    public ResponseEntity<List<AlunoDisciplina>> porDisciplina(@PathVariable Long idDisciplina) {
        return ResponseEntity.ok(repository.findByDisciplinaId(idDisciplina));
    }

    @GetMapping("/aluno/{idAluno}")
    public ResponseEntity<List<AlunoDisciplina>> porAluno(@PathVariable Long idAluno) {
        return ResponseEntity.ok(repository.findByAlunoId(idAluno));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desmatricular(@PathVariable Long id) {
        service.desmatricular(id);
        return ResponseEntity.noContent().build();
    }

}
