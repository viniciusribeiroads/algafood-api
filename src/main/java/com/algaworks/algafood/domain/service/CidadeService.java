package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.repository.CidadeRepository;
import com.algaworks.algafood.repository.EstadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class CidadeService {

    private final CidadeRepository cidadeRepository;

    private final EstadoService estadoService;

    private final EstadoRepository estadoRepository;

    public List<Cidade> listar() {
        return cidadeRepository.findAll();
    }

    public Cidade buscarPor(Long id) {
        if (!cidadeRepository.findById(id).isPresent()) {
            throw new EntidadeNaoEncontradaException(String.format("Entidade com id %d nao encontrada.", id));
        }
        return cidadeRepository.findById(id).get();
    }

    public Cidade salvar(Cidade cidade) {

        Estado estado = estadoService.buscarOuFalhar(cidade.getEstado().getId());
//        Estado estado = estadoRepository
//                .findById(cidade.getEstado().getId())
//                .orElseThrow( () -> new NegocioException(String.format("Nao existe estado cadastrado com o codigo %d", cidade.getEstado().getId())));
        cidade.setEstado(estado);
        return cidadeRepository.save(cidade);
    }

    public Cidade atualizar(Long id, Cidade cidade) {
        if (!nonNull(cidadeRepository.findById(id))) {
            throw new EntidadeNaoEncontradaException(String.format("Entidade com id %d nao encontrada.", id));
        }
        cidade.setId(id);
        return cidadeRepository.save(cidade);
    }

    public Cidade buscarOuFalhar(Long cidadeId) {
        return cidadeRepository
                .findById(cidadeId).orElseThrow(
                        () -> new EntidadeNaoEncontradaException(String.format("Cidade id %d nao encontrada", cidadeId))
                );
    }

}
