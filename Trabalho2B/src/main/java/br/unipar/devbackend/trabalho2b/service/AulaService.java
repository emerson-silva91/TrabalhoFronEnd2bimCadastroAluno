package br.unipar.devbackend.trabalho2b.service;

import br.unipar.devbackend.trabalho2b.dto.AulasDadasDTO;
import br.unipar.devbackend.trabalho2b.dto.PresencaDTO;
import br.unipar.devbackend.trabalho2b.model.AulasDadas;
import br.unipar.devbackend.trabalho2b.model.AulasDadasPresencas;
import br.unipar.devbackend.trabalho2b.model.Aluno;
import br.unipar.devbackend.trabalho2b.model.Disciplina;
import br.unipar.devbackend.trabalho2b.repository.AulasDadasPresencasRepository;
import br.unipar.devbackend.trabalho2b.repository.AulasDadasRepository;
import br.unipar.devbackend.trabalho2b.repository.AlunoRepository;
import br.unipar.devbackend.trabalho2b.repository.DisciplinaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AulaService {

    private final AulasDadasRepository aulasDadasRepository;
    private final AulasDadasPresencasRepository aulasDadasPresencasRepository;
    private final AlunoRepository alunoRepository;
    private final DisciplinaRepository disciplinaRepository;

    public AulasDadas criarAula(Long idDisciplina, AulasDadasDTO dto) {
        Disciplina disciplina = disciplinaRepository.findById(idDisciplina)
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada"));

        AulasDadas aula = new AulasDadas();
        aula.setDisciplina(disciplina);
        aula.setData(dto.getData());
        aula.setObservacoes(dto.getObservacoes());

        return aulasDadasRepository.save(aula);
    }

    public void registrarPresencas(Long idAula, List<PresencaDTO> presencasDTO) {
        AulasDadas aula = aulasDadasRepository.findById(idAula)
                .orElseThrow(() -> new RuntimeException("Aula não encontrada"));

        for (PresencaDTO dto : presencasDTO) {
            Aluno aluno = alunoRepository.findById(dto.getIdAluno())
                    .orElseThrow(() -> new RuntimeException("Aluno não encontrado: " + dto.getIdAluno()));

            AulasDadasPresencas presenca = new AulasDadasPresencas();
            presenca.setAulaDada(aula);
            presenca.setAluno(aluno);
            presenca.setFalta(dto.getFalta());

            aulasDadasPresencasRepository.save(presenca);
        }
    }

    public long contarFaltasAlunoNaDisciplina(Long idAluno, Long idDisciplina) {
        return aulasDadasPresencasRepository.countByAlunoIdAndAulaDadaDisciplinaIdAndFaltaTrue(idAluno, idDisciplina);
    }
}
