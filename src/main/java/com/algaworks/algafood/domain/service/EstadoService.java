package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EstadoNaoEncontradoException;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.repository.EstadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EstadoService {

    public static final String ESTADO_NAO_ENCONTRADO = "Estado de id %d nao encontrado";
    private final EstadoRepository estadoRepository;

    public List<Estado> listarTodos() {
        return estadoRepository.findAll();
    }

    public Estado listarPor(Long id) {
        Optional<Estado> estado = estadoRepository.findById(id);
        if (estado.isEmpty()) {
            throw new EstadoNaoEncontradoException(String.format(ESTADO_NAO_ENCONTRADO + ".", id));
        }
        return estado.get();
    }

    public Estado salvar(Estado estado) {
        return estadoRepository.save(estado);
    }

    public Estado atualizar(Long id, Estado estado) {
        Optional<Estado> estadoSalvo = estadoRepository.findById(id);
        if (estadoSalvo.isEmpty()) {
            throw new EstadoNaoEncontradoException(String.format(ESTADO_NAO_ENCONTRADO + ".", id));
        }
        estadoSalvo.get().setNome(estado.getNome());
        return estadoRepository.save(estadoSalvo.get());
    }

    public Estado buscarOuFalhar(Long estadoId) {
        return estadoRepository.findById(estadoId)
                .orElseThrow(() ->
                        new EstadoNaoEncontradoException(String.format(ESTADO_NAO_ENCONTRADO, estadoId)));
    }
}
