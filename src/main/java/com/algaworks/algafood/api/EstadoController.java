package com.algaworks.algafood.api;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/estados")
public class EstadoController {

    @Autowired
    private EstadoService er;

    @GetMapping
    private List<Estado> listar() {
        return er.listarTodos();
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> buscarPor(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(er.listarPor(id));
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    private Estado salvar(@RequestBody Estado estado) {
        return er.salvar(estado);
    }

    @PutMapping("/{id}")
    private ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Estado estado) {
        try {
            return ResponseEntity.ok(er.atualizar(id, estado));
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }
}
