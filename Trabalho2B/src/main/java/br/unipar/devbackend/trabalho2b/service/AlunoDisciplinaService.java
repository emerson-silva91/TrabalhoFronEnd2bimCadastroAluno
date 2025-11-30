package br.unipar.devbackend.trabalho2b.service;

import br.unipar.devbackend.trabalho2b.model.Aluno;
import br.unipar.devbackend.trabalho2b.model.AlunoDisciplina;
import br.unipar.devbackend.trabalho2b.model.Disciplina;
import br.unipar.devbackend.trabalho2b.model.SituacaoAlunoDisciplina;
import br.unipar.devbackend.trabalho2b.repository.AlunoDisciplinaRepository;
import br.unipar.devbackend.trabalho2b.repository.AlunoRepository;
import br.unipar.devbackend.trabalho2b.repository.DisciplinaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlunoDisciplinaService {
    private final AlunoDisciplinaRepository repository;
    private final AlunoRepository alunoRepository;
    private final DisciplinaRepository disciplinaRepository;

    public AlunoDisciplina matricular(Long alunoId, Long disciplinaId) {
        Aluno aluno = alunoRepository.findById(alunoId).orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
        Disciplina disciplina = disciplinaRepository.findById(disciplinaId).orElseThrow(() -> new RuntimeException("Disciplina não encontrada"));

        repository.findByAlunoIdAndDisciplinaId(alunoId, disciplinaId)
                .ifPresent(a -> { throw new RuntimeException("Aluno já matriculado nesta disciplina"); });

        AlunoDisciplina ad = new AlunoDisciplina();
        ad.setAluno(aluno);
        ad.setDisciplina(disciplina);
        ad.setMatriculado(true);
        ad.setSituacao(SituacaoAlunoDisciplina.EM_CURSO);
        ad.setFaltas1Bim(0);
        ad.setFaltas2Bim(0);
        return repository.save(ad);
    }

    public void desmatricular(Long alunoDisciplinaId) {
        AlunoDisciplina ad = repository.findById(alunoDisciplinaId).orElseThrow(() -> new RuntimeException("Matrícula não encontrada"));
        ad.setMatriculado(false);
        repository.save(ad);
    }

}
