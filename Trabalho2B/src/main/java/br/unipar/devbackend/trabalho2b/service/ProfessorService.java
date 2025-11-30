package br.unipar.devbackend.trabalho2b.service;

import br.unipar.devbackend.trabalho2b.model.Professor;
import br.unipar.devbackend.trabalho2b.repository.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {

    private final ProfessorRepository repository;

    public ProfessorService(ProfessorRepository repository) {
        this.repository = repository;
    }

    public Professor salvar(Professor professor) {
        return repository.save(professor);
    }

    public List<Professor> listar() {
        return repository.findAll();
    }

    public Professor buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado: " + id));
    }



    public Professor atualizar(Long id, Professor dadosAtualizados) {
        Professor existente = buscarPorId(id);

        existente.setNome(dadosAtualizados.getNome());
        existente.setCpf(dadosAtualizados.getCpf());
        existente.setMatricula(dadosAtualizados.getMatricula());

        return repository.save(existente);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
    public Professor atualizarParcial(Long id, Professor novosDados) {
        Professor existente = buscarPorId(id);

        if (novosDados.getNome() != null) {
            existente.setNome(novosDados.getNome());
        }

        if (novosDados.getCpf() != null) {
            existente.setCpf(novosDados.getCpf());
        }

        if (novosDados.getMatricula() != null) {
            existente.setMatricula(novosDados.getMatricula());
        }

        return repository.save(existente);
    }



}
