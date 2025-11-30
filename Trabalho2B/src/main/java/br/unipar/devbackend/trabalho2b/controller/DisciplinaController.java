package br.unipar.devbackend.trabalho2b.controller;

import br.unipar.devbackend.trabalho2b.dto.DisciplinaDTO;
import br.unipar.devbackend.trabalho2b.model.Disciplina;
import br.unipar.devbackend.trabalho2b.service.DisciplinaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disciplina")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class DisciplinaController {

    private final DisciplinaService service;

    @PostMapping
    public ResponseEntity<Disciplina> criar(@RequestBody DisciplinaDTO dto) {
        return ResponseEntity.ok(service.salvar(dto));
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Disciplina> atualizar(@PathVariable Long codigo, @RequestBody DisciplinaDTO dto) {
        Disciplina disciplinaAtualizada = service.atualizar(codigo, dto);
        return ResponseEntity.ok(disciplinaAtualizada);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Disciplina> buscar(@PathVariable String codigo) {
        return ResponseEntity.ok(service.buscarPorCodigo(codigo));
    }

    @GetMapping("/todas")
    public ResponseEntity<List<Disciplina>> todas() {
        return ResponseEntity.ok(service.todas());
    }

    @GetMapping("/professor/{idProfessor}")
    public ResponseEntity<List<Disciplina>> porProfessor(@PathVariable Long idProfessor) {
        return ResponseEntity.ok(service.porProfessor(idProfessor));
    }
}
