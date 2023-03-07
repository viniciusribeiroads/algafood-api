package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.repository.CidadeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class CidadeService {

    private final CidadeRepository cidadeRepository;

    public List<Cidade> listar() {
        return cidadeRepository.findAll();
    }

    public Cidade buscarPor(Long id) {
        if (!nonNull(cidadeRepository.findById(id))) {
            throw new EntidadeNaoEncontradaException(String.format("Entidade com id %d nao encontrada.", id));
        }
        return cidadeRepository.findById(id).get();
    }

    public Cidade salvar(Cidade cidade) {
        return cidadeRepository.save(cidade);
    }

    public Cidade atualizar(Long id, Cidade cidade) {
        if (!nonNull(cidadeRepository.findById(id))) {
            throw new EntidadeNaoEncontradaException(String.format("Entidade com id %d nao encontrada.", id));
        }
        cidade.setId(id);
        return cidadeRepository.save(cidade);
    }

}
