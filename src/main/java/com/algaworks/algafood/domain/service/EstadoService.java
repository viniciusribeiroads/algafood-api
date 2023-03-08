package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.repository.EstadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EstadoService {

    private final EstadoRepository estadoRepository;

    public List<Estado> listarTodos() {
        return estadoRepository.findAll();
    }

    public Estado listarPor(Long id) {
        Optional<Estado> estado = estadoRepository.findById(id);
        if (estado.isEmpty()) {
            throw new EntidadeNaoEncontradaException(String.format("Estado de id %d nao encontrado.", id));
        }
        return estado.get();
    }

    public Estado salvar(Estado estado) {
        return estadoRepository.save(estado);
    }

    public Estado atualizar(Long id, Estado estado) {
        Optional<Estado> estadoSalvo = estadoRepository.findById(id);
        if (estadoSalvo.isEmpty()) {
            throw new EntidadeNaoEncontradaException(String.format("Estado de id %d nao encontrado.", id));
        }
        estadoSalvo.get().setNome(estado.getNome());
        return estadoRepository.save(estadoSalvo.get());
    }

    public Estado buscarOuFalhar(Long estadoId) {
        return estadoRepository.findById(estadoId)
                .orElseThrow(() ->
                        new EntidadeNaoEncontradaException(String.format("Estado de id: %d nao encontrado", estadoId)));
    }
}
