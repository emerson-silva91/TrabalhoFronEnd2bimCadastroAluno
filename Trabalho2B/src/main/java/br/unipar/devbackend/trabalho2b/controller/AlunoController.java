package br.unipar.devbackend.trabalho2b.controller;

import br.unipar.devbackend.trabalho2b.model.Aluno;
import br.unipar.devbackend.trabalho2b.model.AlunoDisciplina;
import br.unipar.devbackend.trabalho2b.service.AlunoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aluno")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AlunoController {

    private final AlunoService service;

    @PostMapping
    public ResponseEntity<Aluno> criar(@RequestBody Aluno aluno) {
        return ResponseEntity.ok(service.salvar(aluno));
    }

    @PutMapping
    public ResponseEntity<Aluno> atualizar(@RequestBody Aluno aluno) {
        return ResponseEntity.ok(service.atualizar(aluno));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscar(id));
    }

    @GetMapping
    public ResponseEntity<List<Aluno>> todos() {
        return ResponseEntity.ok(service.todos());
    }

    @GetMapping("/{id}/boletim")
    public ResponseEntity<List<AlunoDisciplina>> boletim(@PathVariable Long id) {
        return ResponseEntity.ok(service.boletim(id));
    }
}
