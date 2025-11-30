package br.unipar.devbackend.trabalho2b.service;

import br.unipar.devbackend.trabalho2b.model.Aluno;
import br.unipar.devbackend.trabalho2b.model.AlunoDisciplina;
import br.unipar.devbackend.trabalho2b.repository.AlunoDisciplinaRepository;
import br.unipar.devbackend.trabalho2b.repository.AlunoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final AlunoDisciplinaRepository alunoDisciplinaRepository;

    public Aluno salvar(Aluno a) { return alunoRepository.save(a); }

    public Aluno atualizar(Aluno a) {
        if (a.getId() == null) throw new RuntimeException("id obrigatório");
        return alunoRepository.save(a);
    }

    public Aluno buscar(Long id) {
        return alunoRepository.findById(id).orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
    }

    public List<Aluno> todos() { return alunoRepository.findAll(); }

    public List<AlunoDisciplina> boletim(Long alunoId) {
        return alunoDisciplinaRepository.findAll().stream()
                .filter(ad -> ad.getAluno() != null && ad.getAluno().getId().equals(alunoId))
                .toList();
    }
}
