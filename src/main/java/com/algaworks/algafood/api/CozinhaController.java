package com.algaworks.algafood.api;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.service.CozinhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Objects.nonNull;

@RestController
@RequestMapping(value = "/cozinhas")
public class CozinhaController {

    @Autowired
    private CozinhaService cozinhaService;

    @GetMapping
    public List<Cozinha> listar() {
        return cozinhaService.findAll();
    }

    @GetMapping(path = "/{cozinhaId}")
    public Cozinha buscar(@PathVariable Long cozinhaId) {
        return cozinhaService.buscarOuFalhar(cozinhaId);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cozinha adicionar(@RequestBody Cozinha cozinha) {
        return cozinhaService.salvar(cozinha);
    }

    @PutMapping(path = "/{cozinhaId}")
    public ResponseEntity<Cozinha> atualizar(@PathVariable Long cozinhaId, @RequestBody Cozinha cozinhaNova) {
        Cozinha cozinhaAtual = cozinhaService.findBy(cozinhaId).get();
        if (!nonNull(cozinhaAtual)) {
            return ResponseEntity.notFound().build();
        }
        BeanUtils.copyProperties(cozinhaNova, cozinhaAtual, "id");
        return ResponseEntity.ok(cozinhaService.salvar(cozinhaAtual));
    }

    @DeleteMapping(path = "/{idCozinha}")
    public ResponseEntity remover(@PathVariable Long idCozinha) {
        try {
            cozinhaService.excluir(idCozinha);
            return ResponseEntity.noContent().build();
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }


    }
}
