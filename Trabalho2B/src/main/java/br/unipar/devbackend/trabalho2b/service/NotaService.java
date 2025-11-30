package br.unipar.devbackend.trabalho2b.service;

import br.unipar.devbackend.trabalho2b.model.AlunoDisciplina;
import br.unipar.devbackend.trabalho2b.model.SituacaoAlunoDisciplina;
import br.unipar.devbackend.trabalho2b.repository.AulasDadasRepository;
import br.unipar.devbackend.trabalho2b.repository.AlunoDisciplinaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotaService {

    private final AlunoDisciplinaRepository repo;
    private final AulasDadasRepository aulasRepo;

    public AlunoDisciplina atualizarPrimeiroBimestre(Long alunoId, Long disciplinaId, Double nota, Integer faltas) {
        AlunoDisciplina ad = repo.findByAlunoIdAndDisciplinaId(alunoId, disciplinaId)
                .orElseThrow(() -> new RuntimeException("AlunoDisciplina não encontrada"));

        ad.setNota1Bim(nota);
        ad.setFaltas1Bim(faltas == null ? 0 : faltas);

        return repo.save(ad);
    }

    public AlunoDisciplina atualizarSegundoBimestre(Long alunoId, Long disciplinaId, Double nota, Integer faltas) {
        AlunoDisciplina ad = repo.findByAlunoIdAndDisciplinaId(alunoId, disciplinaId)
                .orElseThrow(() -> new RuntimeException("AlunoDisciplina não encontrada"));

        ad.setNota2Bim(nota);
        ad.setFaltas2Bim(faltas == null ? 0 : faltas);

        double n1 = ad.getNota1Bim() == null ? 0.0 : ad.getNota1Bim();
        double n2 = ad.getNota2Bim() == null ? 0.0 : ad.getNota2Bim();
        double media = (n1 + n2) / 2.0;

        int f1 = ad.getFaltas1Bim() == null ? 0 : ad.getFaltas1Bim();
        int f2 = ad.getFaltas2Bim() == null ? 0 : ad.getFaltas2Bim();
        int totalFaltas = f1 + f2;

        long totalAulas = aulasRepo.findAll().stream()
                .filter(a -> a.getDisciplina() != null && a.getDisciplina().getId().equals(ad.getDisciplina().getId()))
                .count();

        double percentualPresenca = 1.0;
        if (totalAulas > 0) {
            percentualPresenca = 1.0 - (totalFaltas / (double) totalAulas);
        }

        if (media >= 6.0 && percentualPresenca >= 0.75) {
            ad.setSituacao(SituacaoAlunoDisciplina.APROVADO);
            ad.setMatriculado(false);
        } else {
            ad.setSituacao(SituacaoAlunoDisciplina.REPROVADO);
        }

        return repo.save(ad);
    }
}
