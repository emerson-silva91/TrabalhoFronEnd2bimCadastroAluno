package br.unipar.devbackend.trabalho2b.service;

import br.unipar.devbackend.trabalho2b.dto.DisciplinaDTO;
import br.unipar.devbackend.trabalho2b.model.Disciplina;
import br.unipar.devbackend.trabalho2b.model.Professor;
import br.unipar.devbackend.trabalho2b.repository.DisciplinaRepository;
import br.unipar.devbackend.trabalho2b.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DisciplinaService {


    private final DisciplinaRepository disciplinaRepository;

    private final ProfessorRepository professorRepository;


    public Disciplina salvar(DisciplinaDTO dto) {

        Professor professor = professorRepository.findById(dto.getProfessorId())
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));

        Disciplina disciplina = new Disciplina();
        disciplina.setCodigo(dto.getCodigo());
        disciplina.setDescricao(dto.getDescricao());
        disciplina.setEmenta(dto.getEmenta());
        disciplina.setProfessor(professor);

        return disciplinaRepository.save(disciplina);
    }


    public Disciplina atualizar(Long codigo, DisciplinaDTO dto) {
        Disciplina disciplina = disciplinaRepository.findById(codigo)
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada"));

        if (dto.getDescricao() != null)
            disciplina.setDescricao(dto.getDescricao());

        if (dto.getEmenta() != null)
            disciplina.setEmenta(dto.getEmenta());

        if (dto.getProfessorId() != null) {
            Professor professor = professorRepository.findById(dto.getProfessorId())
                    .orElseThrow(() -> new RuntimeException("Professor não encontrado"));
            disciplina.setProfessor(professor);
        }

        return disciplinaRepository.save(disciplina);
    }

    public Disciplina buscarPorCodigo(String codigo) {
        return disciplinaRepository.findByCodigo(codigo)
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada"));
    }

    public List<Disciplina> todas() {
        return disciplinaRepository.findAll();
    }

    public List<Disciplina> porProfessor(Long professorId) {
        return disciplinaRepository.findByProfessorId(professorId);
    }
}