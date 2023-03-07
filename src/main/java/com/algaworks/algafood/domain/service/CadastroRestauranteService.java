package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeInvalidaException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.repository.CozinhaRepository;
import com.algaworks.algafood.repository.RestauranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class CadastroRestauranteService {

    @Autowired
    private final RestauranteRepository restauranteRepository;

    @Autowired
    private final CozinhaRepository cozinhaRepository;

    public List<Restaurante> listar() {
        return restauranteRepository.findAll();
    }

    public Restaurante listarPor(Long id) {
        Restaurante restaurante = restauranteRepository.findById(id).get();

        if (restaurante == null) {
            throw new EmptyResultDataAccessException(1);
        }
        return restaurante;
    }

    public Restaurante salvar(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();
        Cozinha cozinha = cozinhaRepository.findById(cozinhaId).get();

        if (!nonNull(cozinha)) {
            throw new EntidadeNaoEncontradaException(String.format("Não existe cadastro de cozinha com o código: %d", cozinhaId));
        }
        restaurante.setCozinha(cozinha);
        return restauranteRepository.save(restaurante);
    }

    public Restaurante atualizar(Restaurante restaurante) {

        try {
            return restauranteRepository.save(restaurante);
        } catch (IllegalArgumentException e) {
            throw new EntidadeNaoEncontradaException("Entidade %d não encontrada");
        } catch (Exception e) {
            throw new EntidadeInvalidaException("Entidade com dados inválidos");
        }
    }
}
